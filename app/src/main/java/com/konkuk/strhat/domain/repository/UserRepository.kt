package com.konkuk.strhat.domain.repository

import com.konkuk.strhat.domain.entity.UserInfoModel

interface UserRepository {
    suspend fun getUserInfo(): Result<UserInfoModel>
}