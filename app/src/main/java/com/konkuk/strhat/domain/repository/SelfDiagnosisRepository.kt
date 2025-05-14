package com.konkuk.strhat.domain.repository

import com.konkuk.strhat.domain.entity.SelfDiagnosisItem

interface SelfDiagnosisRepository {
    suspend fun getSelfDiagnosisQuestionList(type: String): Result<List<SelfDiagnosisItem>>
}