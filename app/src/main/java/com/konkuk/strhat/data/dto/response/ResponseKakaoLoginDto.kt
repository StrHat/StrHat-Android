package com.konkuk.strhat.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseKakaoLoginDto(
    @SerialName("userExists")
    val userExists: Boolean,
    @SerialName("kakaoId")
    val kakaoId: Long
)