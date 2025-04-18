package com.konkuk.strhat.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDiaryExistenceDto(
    @SerialName("hasDiary")
    val hasDiary: Boolean,
    @SerialName("emotion")
    val emotion: Int?,
    @SerialName("summary")
    val summary: String?
)
