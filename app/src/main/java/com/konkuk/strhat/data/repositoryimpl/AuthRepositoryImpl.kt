package com.konkuk.strhat.data.repositoryimpl

import com.konkuk.strhat.data.datasource.AuthDataSource
import com.konkuk.strhat.data.mapper.toRequestkakaoLoginDto
import com.konkuk.strhat.domain.entity.KakaoAccessTokenModel
import com.konkuk.strhat.domain.entity.KakaoLoginModel
import com.konkuk.strhat.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
): AuthRepository {
    override suspend fun postKakaoLogin(kakaoAccessToken: KakaoAccessTokenModel): Result<KakaoLoginModel> =
        runCatching {
            val response = authDataSource.postKakaoLogin(kakaoAccessToken.toRequestkakaoLoginDto())

            if (!response.isSuccessful || response.body() == null) {
                throw Exception("카카오 로그인 실패: ${response.code()}")
            }

            val responseBody = response.body()!!.response
            val headers = response.headers()

            val userExists = responseBody.userExists
            val kakaoId = responseBody.kakaoId

            val accessToken = if (userExists) headers["authorization"] else null
            val refreshToken = if (userExists) headers["refresh-token"] else null

            KakaoLoginModel(
                userExists = userExists,
                kakaoId = kakaoId,
                authorization = accessToken,
                refreshToken = refreshToken
            )
        }
}