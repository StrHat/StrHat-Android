package com.konkuk.strhat.domain.usecase

import com.konkuk.strhat.domain.repository.AuthRepository
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend operator fun invoke(): Result<Unit> =
        authRepository.postSignOut()
}