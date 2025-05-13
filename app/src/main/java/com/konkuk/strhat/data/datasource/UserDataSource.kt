package com.konkuk.strhat.data.datasource

import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.response.ResponseUserInfoDto

interface UserDataSource {
    suspend fun getUserInfo(): BaseResponse<ResponseUserInfoDto>
}