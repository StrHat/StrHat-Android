package com.konkuk.strhat.feature.diary.state

data class DiaryAIFeedbackState(
    val diaryAIFeedbackSummary: String = "",
    val diaryAIFeedbackPositiveKeywords: List<String> = emptyList(),
    val diaryAIFeedbackNegativeKeywords: List<String> = emptyList(),
    val diaryAIFeedbackRecommendation: String = ""
)
