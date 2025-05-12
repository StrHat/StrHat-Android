package com.konkuk.strhat.data.datasource

import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.request.RequestkakaoLoginDto
import com.konkuk.strhat.data.dto.response.ResponseKakaoLoginDto
import retrofit2.Response

interface AuthDataSource{
    suspend fun postKakaoLogin(kakaoAccessToken: RequestkakaoLoginDto): Response<BaseResponse<ResponseKakaoLoginDto>>
}