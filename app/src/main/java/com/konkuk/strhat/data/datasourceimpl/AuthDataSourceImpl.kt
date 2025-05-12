package com.konkuk.strhat.data.datasourceimpl

import com.konkuk.strhat.data.datasource.AuthDataSource
import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.request.RequestSignUpDto
import com.konkuk.strhat.data.dto.request.RequestkakaoLoginDto
import com.konkuk.strhat.data.dto.response.ResponseKakaoLoginDto
import com.konkuk.strhat.data.service.AuthService
import retrofit2.Response
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authService: AuthService
): AuthDataSource {
    override suspend fun postKakaoLogin(kakaoAccessToken: RequestkakaoLoginDto): Response<BaseResponse<ResponseKakaoLoginDto>> =
        authService.kakaoLogin(kakaoLoginRequest = kakaoAccessToken)

    override suspend fun postSignUp(signUpRequest: RequestSignUpDto): Response<Unit> =
        authService.signUp(signUpRequest = signUpRequest)

}