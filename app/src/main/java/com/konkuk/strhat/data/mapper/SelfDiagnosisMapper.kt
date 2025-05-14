package com.konkuk.strhat.data.mapper

import com.konkuk.strhat.data.dto.response.ResponseSelfDiagnosisQuestionDto
import com.konkuk.strhat.domain.entity.SelfDiagnosisItem

fun ResponseSelfDiagnosisQuestionDto.toSelfDiagnosisQuestionModel() = SelfDiagnosisItem(
    selfDiagnosisIndex = selfDiagnosisIndex,
    selfDiagnosisQuestion = selfDiagnosisQuestion
)

fun List<ResponseSelfDiagnosisQuestionDto>.toSelfDiagnosisQuestionListModel(): List<SelfDiagnosisItem> =
    this.map {
        it.toSelfDiagnosisQuestionModel()
    }