package com.konkuk.strhat.data.datasource

import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.request.RequestSignUpDto
import com.konkuk.strhat.data.dto.request.RequestKakaoLoginDto
import com.konkuk.strhat.data.dto.response.ResponseKakaoLoginDto
import retrofit2.Response

interface AuthDataSource{
    suspend fun postKakaoLogin(kakaoAccessToken: RequestKakaoLoginDto): Response<BaseResponse<ResponseKakaoLoginDto>>
    suspend fun postSignUp(signUpRequest: RequestSignUpDto): Response<Unit>
}