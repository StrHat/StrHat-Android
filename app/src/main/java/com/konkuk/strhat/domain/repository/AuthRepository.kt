package com.konkuk.strhat.domain.repository

import com.konkuk.strhat.domain.entity.KakaoAccessTokenModel
import com.konkuk.strhat.domain.entity.KakaoLoginModel
import com.konkuk.strhat.domain.entity.SignUpModel
import com.konkuk.strhat.domain.entity.TokenModel

interface AuthRepository {
    suspend fun postKakaoLogin(kakaoAccessToken: KakaoAccessTokenModel): Result<KakaoLoginModel>
    suspend fun postSignUp(signUpRequest: SignUpModel): Result<TokenModel>
}