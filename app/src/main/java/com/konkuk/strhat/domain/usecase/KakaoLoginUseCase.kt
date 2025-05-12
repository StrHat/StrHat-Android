package com.konkuk.strhat.domain.usecase

import com.konkuk.strhat.domain.entity.KakaoAccessTokenModel
import com.konkuk.strhat.domain.entity.KakaoLoginModel
import com.konkuk.strhat.domain.repository.AuthRepository
import javax.inject.Inject

class KakaoLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend operator fun invoke(kakaoAccessToken: KakaoAccessTokenModel): Result<KakaoLoginModel> =
        authRepository.postKakaoLogin(kakaoAccessToken = kakaoAccessToken)
}