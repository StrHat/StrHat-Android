package com.konkuk.strhat.domain.repository

import com.konkuk.strhat.domain.entity.KakaoAccessTokenModel
import com.konkuk.strhat.domain.entity.KakaoLoginModel

interface AuthRepository {
    suspend fun postKakaoLogin(kakaoAccessToken: KakaoAccessTokenModel): Result<KakaoLoginModel>
}