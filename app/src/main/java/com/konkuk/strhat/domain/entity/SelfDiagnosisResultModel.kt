package com.konkuk.strhat.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class SelfDiagnosisResultModel(
    val nickname: String,
    val score: Int ?= null,
    val type: String ?= null,
    val selfDiagnosisLevel: String ?= null
)
