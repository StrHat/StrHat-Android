package com.konkuk.strhat.data.datasourceimpl

import com.konkuk.strhat.data.datasource.SelfDiagnosisDataSource
import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.response.ResponseSelfDiagnosisQuestionDto
import com.konkuk.strhat.data.service.SelfDiagnosisService
import javax.inject.Inject

class SelfDiagnosisDataSourceImpl @Inject constructor(
    private val selfDiagnosisService: SelfDiagnosisService
) : SelfDiagnosisDataSource {
    override suspend fun getSelfDiagnosisQuestionList(type: String): BaseResponse<List<ResponseSelfDiagnosisQuestionDto>> =
        selfDiagnosisService.getSelfDiagnosisQuestionList(type)
}