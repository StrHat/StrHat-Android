package com.konkuk.strhat.feature.mypage.state

import java.time.LocalDate

data class MySelfDiagnosisRecordResultState(
    val nickname: String = "",
    val testType: String = "",
    val stressScore: Int = 0,
    val stressLevel: String = "",
    val stressLevelDescription: String = "",
    val testTypeDescription: String = "",
    val selectedDateString: String = "",
    val selectedDate: LocalDate? = null
)
