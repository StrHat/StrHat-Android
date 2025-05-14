package com.konkuk.strhat.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseHomeDto(
    @SerialName("hasDiary")
    val hasDiary: Boolean,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("emotion")
    val emotion: Int?,
    @SerialName("positiveEmotions")
    val positiveEmotions: List<String>?,
    @SerialName("stressReliefSuggestion")
    val stressReliefSuggestion: String?,
    @SerialName("stressScore")
    val stressScore: Int?,
    @SerialName("stressLevel")
    val stressLevel: String?
)
