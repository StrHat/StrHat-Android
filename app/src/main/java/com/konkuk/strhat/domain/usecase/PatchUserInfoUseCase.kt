package com.konkuk.strhat.domain.usecase

import com.konkuk.strhat.domain.repository.UserRepository
import javax.inject.Inject

class PatchUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        nickname: String,
        birth: Int,
        gender: String,
        job: String
    ) = userRepository.patchUserInfo(
        nickname = nickname,
        birth = birth,
        gender = gender,
        job = job
    )
}