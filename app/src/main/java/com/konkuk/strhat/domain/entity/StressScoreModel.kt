package com.konkuk.strhat.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class StressScoreModel(
    val nickname: String,
    val stressScore: Int,
    val level: String,
    val analysis: String,
    val stressScoreDate: String
)
