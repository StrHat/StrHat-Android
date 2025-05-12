package com.konkuk.strhat.domain.usecase

import com.konkuk.strhat.domain.entity.SignUpModel
import com.konkuk.strhat.domain.entity.TokenModel
import com.konkuk.strhat.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend operator fun invoke(signUpRequest: SignUpModel): Result<TokenModel> =
        authRepository.postSignUp(signUpRequest = signUpRequest)
}