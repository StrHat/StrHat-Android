package com.konkuk.strhat.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class WeeklyStressScoreModel(
    val nickname: String,
    val weeklySummary: String,
    val stressLevels: List<Int?>,
    val emotionLevels: List<Int?>,
    val startDate: String,
    val endDate: String
)
