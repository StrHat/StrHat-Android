package com.konkuk.strhat.feature.selfdiagnosis.state

data class SelfDiagnosisResultState(
    val nickname: String = "",
    val testType: String = "",
    val stressScore: Int = 0,
    val stressLevel: String = "",
    val stressLevelDescription: String = "",
    val testTypeDescription: String = ""
)
