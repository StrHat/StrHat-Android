package com.konkuk.strhat.data.mapper

import com.konkuk.strhat.data.dto.request.RequestSelfDiagnosisDto
import com.konkuk.strhat.data.dto.response.ResponseSelfDiagnosisQuestionDto
import com.konkuk.strhat.data.dto.response.ResponseSelfDiagnosisResultDto
import com.konkuk.strhat.domain.entity.SelfDiagnosisItem
import com.konkuk.strhat.domain.entity.SelfDiagnosisModel
import com.konkuk.strhat.domain.entity.SelfDiagnosisResultModel

fun ResponseSelfDiagnosisQuestionDto.toSelfDiagnosisQuestionModel() = SelfDiagnosisItem(
    selfDiagnosisIndex = selfDiagnosisIndex,
    selfDiagnosisQuestion = selfDiagnosisQuestion
)

fun List<ResponseSelfDiagnosisQuestionDto>.toSelfDiagnosisQuestionListModel(): List<SelfDiagnosisItem> =
    this.map {
        it.toSelfDiagnosisQuestionModel()
    }

fun ResponseSelfDiagnosisResultDto.toSelfDiagnosisResultModel() = SelfDiagnosisResultModel(
    nickname = nickname,
    score = score,
    type = type,
    selfDiagnosisLevel = selfDiagnosisLevel
)

fun SelfDiagnosisModel.toSelfDiagnosisRequestDto() = RequestSelfDiagnosisDto(
    type = type,
    selfDiagnosisScore = selfDiagnosisScore
)