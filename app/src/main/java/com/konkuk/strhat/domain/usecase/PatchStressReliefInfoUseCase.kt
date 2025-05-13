package com.konkuk.strhat.domain.usecase

import com.konkuk.strhat.domain.repository.UserRepository
import javax.inject.Inject

class PatchStressReliefInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend operator fun invoke(stressRelief: String) = userRepository.patchStressReliefInfo(stressRelief)
}