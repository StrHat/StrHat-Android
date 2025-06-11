package com.konkuk.strhat.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseWeeklyStressScoreDto(
    val nickname: String,
    val weeklySummary: String,
    val stressLevels: List<Int?>,
    val emotionLevels: List<Int?>,
    val startDate: String,
    val endDate: String
)
