package com.konkuk.strhat.data.repositoryimpl

import com.konkuk.strhat.data.datasource.UserDataSource
import com.konkuk.strhat.data.mapper.toUserInfoModel
import com.konkuk.strhat.domain.entity.UserInfoModel
import com.konkuk.strhat.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {
    override suspend fun getUserInfo(): Result<UserInfoModel> =
        runCatching {
            val response = userDataSource.getUserInfo()
            response.response.toUserInfoModel()
        }

    override suspend fun patchHobbyHealingInfo(hobbyHealingStyle: String): Result<Unit> =
        runCatching {
            userDataSource.patchHobbyHealingInfo(hobbyHealingStyle)
        }

    override suspend fun patchStressReliefInfo(stressReliefStyle: String): Result<Unit> =
        runCatching {
            userDataSource.patchStressReliefInfo(stressReliefStyle)
        }

}