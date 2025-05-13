package com.konkuk.strhat.data.service

import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.request.RequestHobbyHealingDto
import com.konkuk.strhat.data.dto.response.ResponseUserInfoDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface UserService {
    @GET("/api/v1/users")
    suspend fun getUserInfo(): BaseResponse<ResponseUserInfoDto>

    @PATCH("/api/v1/users/hobby-healing-style")
    suspend fun patchHobbyHealingInfo(
        @Body requestHobbyHealingDto: RequestHobbyHealingDto
    ): Response<Unit>
}