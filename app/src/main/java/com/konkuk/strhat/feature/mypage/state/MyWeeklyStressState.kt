package com.konkuk.strhat.feature.mypage.state

data class MyWeeklyStressState(
    val nickname: String = "",
    val weeklySummary: String = "",
    val weeklyStressScores: List<Int> = emptyList(),
    val weeklyEmotionScores: List<Int> = emptyList()
)
