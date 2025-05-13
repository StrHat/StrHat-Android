package com.konkuk.strhat.data.datasource

import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.request.RequestUserInfoDto
import com.konkuk.strhat.data.dto.response.ResponseUserInfoDto
import retrofit2.Response

interface UserDataSource {
    suspend fun getUserInfo(): BaseResponse<ResponseUserInfoDto>
    suspend fun patchUserInfo(userInfo: RequestUserInfoDto): Response<Unit>
    suspend fun patchHobbyHealingInfo(hobbyHealingStyle: String): Response<Unit>
    suspend fun patchStressReliefInfo(stressReliefStyle: String): Response<Unit>
    suspend fun patchPersonalityInfo(personality: String): Response<Unit>
}