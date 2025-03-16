package com.konkuk.strhat.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseHomeDto(
    val nickname: String,
    val emotion: Int,
    val positiveEmotions: List<String>,
    val stressReliefSuggestion: String,
    val stressScore: Int,
    val stressLevel: String
)
