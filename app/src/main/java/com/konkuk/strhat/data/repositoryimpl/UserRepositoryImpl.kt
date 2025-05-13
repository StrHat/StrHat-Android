package com.konkuk.strhat.data.repositoryimpl

import com.konkuk.strhat.data.datasource.UserDataSource
import com.konkuk.strhat.data.mapper.toRequestUserInfoDto
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

    override suspend fun patchUserInfo(
        nickname: String,
        birth: Int,
        gender: String,
        job: String
    ): Result<Unit> =
        runCatching {
            val dto = toRequestUserInfoDto(nickname, birth, gender, job)
            userDataSource.patchUserInfo(dto)
        }.mapCatching {
            if (!it.isSuccessful) {
                throw Exception("유저 정보 수정 실패: ${it.code()}")
            }
        }

    override suspend fun patchHobbyHealingInfo(hobbyHealingStyle: String): Result<Unit> =
        runCatching {
            userDataSource.patchHobbyHealingInfo(hobbyHealingStyle)
        }

    override suspend fun patchStressReliefInfo(stressReliefStyle: String): Result<Unit> =
        runCatching {
            userDataSource.patchStressReliefInfo(stressReliefStyle)
        }

    override suspend fun patchPersonalityInfo(personality: String): Result<Unit> =
        runCatching {
            userDataSource.patchPersonalityInfo(personality)
        }

}