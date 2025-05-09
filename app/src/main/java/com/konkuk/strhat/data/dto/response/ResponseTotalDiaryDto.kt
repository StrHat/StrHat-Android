package com.konkuk.strhat.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseTotalDiaryDto(
    @SerialName("content")
    val content: String,
    @SerialName("diaryId")
    val diaryId: Int
)
