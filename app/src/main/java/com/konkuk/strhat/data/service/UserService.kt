package com.konkuk.strhat.data.service

import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.request.RequestHobbyHealingDto
import com.konkuk.strhat.data.dto.request.RequestPersonalityDto
import com.konkuk.strhat.data.dto.request.RequestStressReliefDto
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

    @PATCH("/api/v1/users/stress-relief-style")
    suspend fun patchStressReliefInfo(
        @Body requestStressReliefDto: RequestStressReliefDto
    ): Response<Unit>

    @PATCH("/api/v1/users/personality")
    suspend fun patchPersonalityInfo(
        @Body requestPersonalityDto: RequestPersonalityDto
    ): Response<Unit>

}