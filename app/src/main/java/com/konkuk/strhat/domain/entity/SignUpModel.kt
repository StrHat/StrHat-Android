package com.konkuk.strhat.domain.entity

import com.konkuk.strhat.domain.type.GenderType
import com.konkuk.strhat.domain.type.JobType

data class SignUpModel(
    val kakaoId: Long,
    val nickname: String,
    val birth: Int,
    val gender: GenderType,
    val job: JobType,
    val hobbyHealingStyle: String,
    val stressReliefStyle: String,
    val personality: String
)