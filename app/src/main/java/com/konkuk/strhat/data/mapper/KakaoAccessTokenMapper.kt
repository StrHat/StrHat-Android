package com.konkuk.strhat.data.mapper

import com.konkuk.strhat.data.dto.request.RequestkakaoLoginDto
import com.konkuk.strhat.domain.entity.KakaoAccessTokenModel

fun KakaoAccessTokenModel.toRequestkakaoLoginDto() = RequestkakaoLoginDto(
    kakaoAccessToken = this.accessToken
)