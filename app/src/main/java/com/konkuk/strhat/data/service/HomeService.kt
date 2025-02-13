package com.konkuk.strhat.data.service

import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.response.ResponseHomeDto
import retrofit2.http.GET

interface HomeService {
    @GET("/api/v1/home")
    suspend fun getHomeData() : BaseResponse<ResponseHomeDto>
}