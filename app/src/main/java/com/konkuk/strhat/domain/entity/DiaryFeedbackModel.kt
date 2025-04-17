package com.konkuk.strhat.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class DiaryFeedbackModel(
    val summary: String,
    val positiveKeywords: List<String>,
    val negativeKeywords: List<String>,
    val stressReliefSuggestions: String
)