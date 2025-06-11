package com.konkuk.strhat.domain.repository

import com.konkuk.strhat.domain.entity.SelfDiagnosisItem
import com.konkuk.strhat.domain.entity.SelfDiagnosisModel
import com.konkuk.strhat.domain.entity.SelfDiagnosisResultModel

interface SelfDiagnosisRepository {
    suspend fun getSelfDiagnosisQuestionList(type: String): Result<List<SelfDiagnosisItem>>
    suspend fun getSelfDiagnosisResult(date: String, type: String): Result<SelfDiagnosisResultModel>
    suspend fun postSelfDiagnosis(request: SelfDiagnosisModel): Result<Unit>
}