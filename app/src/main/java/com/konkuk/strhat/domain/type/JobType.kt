package com.konkuk.strhat.domain.type

enum class JobType(
    val displayName: String
) {
    STUDENT(displayName = "대학생"),
    JOBSEEKER(displayName = "취준생"),
    WORKER(displayName = "직장인");

    companion object {
        fun fromDisplay(display: String): JobType =
            entries.find { it.displayName == display } ?: STUDENT
    }
}