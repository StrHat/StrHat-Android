package com.konkuk.strhat.feature.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.konkuk.strhat.core.network.TokenManager
import com.konkuk.strhat.domain.entity.KakaoAccessTokenModel
import com.konkuk.strhat.domain.usecase.KakaoLoginUseCase
import com.konkuk.strhat.feature.login.model.LoginResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val kakaoLoginUseCase: KakaoLoginUseCase,
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _loginResult = MutableStateFlow<LoginResult?>(null)
    val loginResult: StateFlow<LoginResult?> = _loginResult

    fun loginWithKakao(context: Context) {
        viewModelScope.launch {
            runCatching {
                suspendCancellableCoroutine { continuation ->
                    performKakaoLogin(context) { token, error ->
                        when {
                            error != null -> continuation.resumeWithException(error)
                            token != null -> continuation.resume(token)
                        }
                    }
                }
            }.onSuccess { token ->
                val accessToken = token.accessToken
                login(KakaoAccessTokenModel(accessToken))
            }.onFailure {
                Timber.tag("kakao login").e(it, "카카오 로그인 실패")
            }
        }
    }

    private fun performKakaoLogin(context: Context, callback: (OAuthToken?, Throwable?) -> Unit) {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) return@loginWithKakaoTalk
                if (token != null) callback(token, null) else UserApiClient.instance.loginWithKakaoAccount(context = context, callback = callback)
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context = context, callback = callback)
        }
    }

    private fun login(accessToken: KakaoAccessTokenModel){
        viewModelScope.launch {
            kakaoLoginUseCase(accessToken)
                .onSuccess { response ->
                    if (response.userExists) {
                        response.authorization?.let { tokenManager.saveToken(it) }
                        response.refreshToken?.let { tokenManager.saveRefreshToken(it) }
                        _loginResult.value = LoginResult(isSuccess = true, isExistingUser = true)
                    } else {
                        tokenManager.saveKakaoId(response.kakaoId)
                        _loginResult.value = LoginResult(
                            isSuccess = true,
                            isExistingUser = false,
                            kakaoId = response.kakaoId.toLong()
                        )
                    }
                }
                .onFailure {
                    Timber.tag("kakao login").e(it, "카카오 로그인 실패")
                }
        }
    }
}