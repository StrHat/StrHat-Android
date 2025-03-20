package com.konkuk.strhat.domain.type

enum class JobType(
    val type: String
) {
    STUDENT(
        type = "대학생"
    ),
    JOBSEEKER(
        type = "취준생"
    ),
    WORKER(
        type = "직장인"
    );

    companion object {
        fun toJobType(job: String): String =
            entries.find { it.name.equals(job, ignoreCase = true) }?.type ?: STUDENT.type
    }
}