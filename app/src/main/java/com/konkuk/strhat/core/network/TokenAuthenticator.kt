package com.konkuk.strhat.core.network

import com.konkuk.strhat.data.dto.request.RequestReissueTokenDto
import com.konkuk.strhat.data.service.ReIssueService
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
    private val retrofit: dagger.Lazy<Retrofit>
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshToken = tokenManager.getRefreshToken()
        if (refreshToken.isBlank()) return null

        Timber.tag("TokenAuthenticator").d("401 감지됨. 토큰 재발급 시도")

        val authService = retrofit.get().create(ReIssueService::class.java)
        val refreshTokenDto = RequestReissueTokenDto("Bearer $refreshToken")

        val tokenResponse = authService.reissueToken(refreshTokenDto).execute()

        return if (tokenResponse.isSuccessful) {
            val newAccessToken = tokenResponse.headers()["authorization"]?.let {
                it.removePrefix("Bearer").trim()
            }
            val newRefreshToken = tokenResponse.headers()["refresh-token"]?.let {
                it.removePrefix("Bearer").trim()
            }

            if (newAccessToken.isNullOrBlank() || newRefreshToken.isNullOrBlank()) {
                Timber.tag("TokenAuthenticator").e("헤더에서 토큰을 읽을 수 없음 → 로그인 필요")
                return null
            }

            tokenManager.saveToken(newAccessToken)
            tokenManager.saveRefreshToken(newRefreshToken)

            response.request.newBuilder()
                .header("Authorization", "Bearer $newAccessToken")
                .build()
        } else {
            Timber.tag("TokenAuthenticator").e("토큰 재발급 실패 → 로그인 필요")
            tokenManager.triggerLogout()
            null
        }
    }
}
