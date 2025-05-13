package com.konkuk.strhat.data.service

import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.response.ResponseUserInfoDto
import retrofit2.http.GET

interface UserService {
    @GET("/api/v1/users")
    suspend fun getUserInfo(): BaseResponse<ResponseUserInfoDto>
}