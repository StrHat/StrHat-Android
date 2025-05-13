package com.konkuk.strhat.data.service

import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.request.RequestSignUpDto
import com.konkuk.strhat.data.dto.request.RequestKakaoLoginDto
import com.konkuk.strhat.data.dto.response.ResponseKakaoLoginDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/v1/auth/kakao")
    suspend fun kakaoLogin(
        @Body kakaoLoginRequest: RequestKakaoLoginDto
    ): Response<BaseResponse<ResponseKakaoLoginDto>>

    @POST("/api/v1/users/sign-up")
    suspend fun signUp(
        @Body signUpRequest: RequestSignUpDto
    ): Response<Unit>

    @POST("/api/v1/users/sign-out")
    suspend fun signOut(): Response<Unit>
}