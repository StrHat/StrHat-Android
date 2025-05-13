package com.konkuk.strhat.data.datasourceimpl

import com.konkuk.strhat.data.datasource.UserDataSource
import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.response.ResponseUserInfoDto
import com.konkuk.strhat.data.service.UserService
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userService: UserService
): UserDataSource {
    override suspend fun getUserInfo(): BaseResponse<ResponseUserInfoDto> =
        userService.getUserInfo()

}