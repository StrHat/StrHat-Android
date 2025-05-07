package com.konkuk.strhat.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestAddDiaryDto(
    @SerialName("date")
    val date: String,
    @SerialName("emotion")
    val emotion: Int,
    @SerialName("content")
    val content: String
)
