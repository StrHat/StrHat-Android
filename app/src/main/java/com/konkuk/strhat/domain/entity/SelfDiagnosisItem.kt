package com.konkuk.strhat.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class SelfDiagnosisItem(
    val selfDiagnosisIndex: Int,
    val selfDiagnosisQuestion: String
)