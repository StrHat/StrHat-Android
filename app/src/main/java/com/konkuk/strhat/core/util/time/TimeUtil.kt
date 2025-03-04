package com.konkuk.strhat.core.util.time

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal val currentDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

internal val currentDate = currentDateTime.date

fun String.toDate(): LocalDate {
    val pattern = Regex("(\\d{1,2})월 (\\d{1,2})일.*")
    val match = pattern.matchEntire(this) ?: throw IllegalArgumentException("Invalid date format")
    val (month, day) = match.destructured
    val currentYear = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).year
    return LocalDate(currentYear, month.toInt(), day.toInt())
}

fun String.toKoreanDay(): String {
    return when (this) {
        "MONDAY" -> "월"
        "TUESDAY" -> "화"
        "WEDNESDAY" -> "수"
        "THURSDAY" -> "목"
        "FRIDAY" -> "금"
        "SATURDAY" -> "토"
        "SUNDAY" -> "일"
        else -> ""
    }
}