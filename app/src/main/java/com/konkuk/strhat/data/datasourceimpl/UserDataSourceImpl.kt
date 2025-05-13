package com.konkuk.strhat.data.datasourceimpl

import com.konkuk.strhat.data.datasource.UserDataSource
import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.request.RequestHobbyHealingDto
import com.konkuk.strhat.data.dto.request.RequestPersonalityDto
import com.konkuk.strhat.data.dto.request.RequestStressReliefDto
import com.konkuk.strhat.data.dto.request.RequestUserInfoDto
import com.konkuk.strhat.data.dto.response.ResponseUserInfoDto
import com.konkuk.strhat.data.service.UserService
import retrofit2.Response
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userService: UserService
): UserDataSource {
    override suspend fun getUserInfo(): BaseResponse<ResponseUserInfoDto> =
        userService.getUserInfo()

    override suspend fun patchUserInfo(userInfo: RequestUserInfoDto): Response<Unit> =
        userService.patchUserInfo(userInfo)

    override suspend fun patchHobbyHealingInfo(hobbyHealingStyle: String): Response<Unit> =
        userService.patchHobbyHealingInfo(RequestHobbyHealingDto(hobbyHealingStyle))

    override suspend fun patchStressReliefInfo(stressReliefStyle: String): Response<Unit> =
        userService.patchStressReliefInfo(RequestStressReliefDto(stressReliefStyle))

    override suspend fun patchPersonalityInfo(personality: String): Response<Unit> =
        userService.patchPersonalityInfo(RequestPersonalityDto(personality))

}