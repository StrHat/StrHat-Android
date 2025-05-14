package com.konkuk.strhat.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseSelfDiagnosisQuestionDto(
    val selfDiagnosisIndex: Int,
    val selfDiagnosisQuestion: String
)
