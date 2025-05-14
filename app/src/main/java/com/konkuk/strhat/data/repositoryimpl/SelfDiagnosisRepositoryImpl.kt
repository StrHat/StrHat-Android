package com.konkuk.strhat.data.repositoryimpl

import com.konkuk.strhat.data.datasource.SelfDiagnosisDataSource
import com.konkuk.strhat.data.mapper.toSelfDiagnosisQuestionListModel
import com.konkuk.strhat.data.mapper.toSelfDiagnosisResultModel
import com.konkuk.strhat.domain.entity.SelfDiagnosisItem
import com.konkuk.strhat.domain.entity.SelfDiagnosisResultModel
import com.konkuk.strhat.domain.repository.SelfDiagnosisRepository
import javax.inject.Inject

class SelfDiagnosisRepositoryImpl @Inject constructor(
    private val selfDiagnosisDataSource: SelfDiagnosisDataSource
) : SelfDiagnosisRepository {
    override suspend fun getSelfDiagnosisQuestionList(type: String): Result<List<SelfDiagnosisItem>> =
        runCatching {
            val response = selfDiagnosisDataSource.getSelfDiagnosisQuestionList(type).response
            response.toSelfDiagnosisQuestionListModel()
        }

    override suspend fun getSelfDiagnosisResult(date: String, type: String): Result<SelfDiagnosisResultModel> =
        runCatching {
            val response = selfDiagnosisDataSource.getSelfDiagnosisResult(date, type).response
            response.toSelfDiagnosisResultModel()
        }
}