package com.konkuk.strhat.domain.usecase

import com.konkuk.strhat.domain.repository.UserRepository
import javax.inject.Inject

class PatchHobbyHealingInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend operator fun invoke(hobbyHealing: String) = userRepository.patchHobbyHealingInfo(hobbyHealing)
}