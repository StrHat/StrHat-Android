package com.konkuk.strhat.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSaveDiaryDto(
    @SerialName("summary")
    val summary: String,
    @SerialName("positiveKeywords")
    val positiveKeywords: List<String>,
    @SerialName("negativeKeywords")
    val negativeKeywords: List<String>,
    @SerialName("stressReliefSuggestions")
    val stressReliefSuggestions: String
)
