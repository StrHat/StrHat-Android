package com.konkuk.strhat.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestHobbyHealingDto(
    @SerialName("hobbyHealingStyle")
    val hobbyHealingStyle: String
)