package com.konkuk.strhat.data.datasource

import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.response.ResponseSelfDiagnosisQuestionDto

interface SelfDiagnosisDataSource {
    suspend fun getSelfDiagnosisQuestionList(type: String): BaseResponse<List<ResponseSelfDiagnosisQuestionDto>>
}