package com.konkuk.strhat.data.mapper

import com.konkuk.strhat.data.dto.request.RequestKakaoLoginDto
import com.konkuk.strhat.domain.entity.KakaoAccessTokenModel

fun KakaoAccessTokenModel.toRequestkakaoLoginDto() = RequestKakaoLoginDto(
    kakaoAccessToken = this.accessToken
)