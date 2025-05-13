package com.konkuk.strhat.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestReissueTokenDto(
    @SerialName("refreshToken")
    val refreshToken: String
)