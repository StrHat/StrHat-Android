package com.konkuk.strhat.feature.diary.state

data class StressScoreState(
    val nickname: String = "",
    val stressScore: Int = 0,
    val level: String = "",
    val analysis: String = ""
)
