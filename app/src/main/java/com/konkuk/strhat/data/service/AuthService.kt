package com.konkuk.strhat.data.service

import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.request.RequestkakaoLoginDto
import com.konkuk.strhat.data.dto.response.ResponseKakaoLoginDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/v1/auth/kakao")
    suspend fun kakaoLogin(
        @Body kakaoLoginRequest: RequestkakaoLoginDto
    ): Response<BaseResponse<ResponseKakaoLoginDto>>
}