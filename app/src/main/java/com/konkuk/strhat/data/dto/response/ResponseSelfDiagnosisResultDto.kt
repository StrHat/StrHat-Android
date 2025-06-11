package com.konkuk.strhat.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseSelfDiagnosisResultDto(
    val nickname: String,
    val score: Int ?= null,
    val type: String ?= null,
    val selfDiagnosisLevel: String ?= null
)
