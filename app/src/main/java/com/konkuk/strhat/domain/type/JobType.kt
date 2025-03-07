package com.konkuk.strhat.domain.type

enum class JobType (
    val type: String
){
    STUDENT(
        type = "대학생"
    ),
    JOBSEEKER(
        type = "취준생"
    ),
    WORKER(
        type = "직장인"
    )
}