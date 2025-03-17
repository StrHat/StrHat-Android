package com.konkuk.strhat.feature.diary.state

import kotlinx.datetime.LocalDate

data class Diary(
    val date: LocalDate,
    val content: String
)