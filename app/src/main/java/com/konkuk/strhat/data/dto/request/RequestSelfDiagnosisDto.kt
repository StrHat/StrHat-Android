package com.konkuk.strhat.data.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestSelfDiagnosisDto(
    val type: String,
    val selfDiagnosisScore: Int
)
