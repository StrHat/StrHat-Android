package com.konkuk.strhat.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestKakaoLoginDto(
    @SerialName("kakaoAccessToken")
    val kakaoAccessToken: String
)