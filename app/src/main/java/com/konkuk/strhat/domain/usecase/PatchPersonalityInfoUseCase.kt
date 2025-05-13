package com.konkuk.strhat.domain.usecase

import com.konkuk.strhat.domain.repository.UserRepository
import javax.inject.Inject

class PatchPersonalityInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend operator fun invoke(personality: String) = userRepository.patchPersonalityInfo(personality)
}