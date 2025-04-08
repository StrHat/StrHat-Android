package com.konkuk.strhat.core.util.time

import com.konkuk.strhat.feature.mypage.state.WeekState
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.minus
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

fun LocalDateTime.toFormattedDateString(): String {
    return "${this.year}년 ${this.monthNumber}월 ${this.dayOfMonth}일"
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

fun LocalDate.generateDiaryContent(): String {
    return "${this.monthNumber}월 ${this.dayOfMonth}일에 작성된 일기 요약"
}

fun getWeekStateFromOffset(offset: Int): WeekState {
    val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    val targetDate = today.minus(offset * 7, DateTimeUnit.DAY)
    return targetDate.toWeekState()
}

fun LocalDate.toWeekState(): WeekState {
    val dayOfWeek = this.dayOfWeek.isoDayNumber
    val startOfWeek = this.minus((dayOfWeek - 1), DateTimeUnit.DAY)

    val firstDayOfMonth = LocalDate(this.year, this.monthNumber, 1)
    val firstDayWeekStart = firstDayOfMonth.minus((firstDayOfMonth.dayOfWeek.isoDayNumber - 1), DateTimeUnit.DAY)

    val weekNumber = ((startOfWeek.daysUntil(firstDayWeekStart) * -1) / 7) + 1

    return WeekState(
        year = this.year,
        month = this.monthNumber,
        weekOfMonth = weekNumber
    )
}