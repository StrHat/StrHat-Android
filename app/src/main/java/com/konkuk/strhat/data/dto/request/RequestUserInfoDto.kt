package com.konkuk.strhat.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestUserInfoDto(
    @SerialName("nickname")
    val nickname: String,
    @SerialName("birth")
    val birth: Int,
    @SerialName("gender")
    val gender: String,
    @SerialName("job")
    val job: String
)