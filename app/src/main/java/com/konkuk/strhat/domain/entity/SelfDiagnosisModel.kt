package com.konkuk.strhat.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class SelfDiagnosisModel(
    val type: String,
    val selfDiagnosisScore: Int
)