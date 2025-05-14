package com.konkuk.strhat.data.datasource

import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.response.ResponseSelfDiagnosisQuestionDto
import com.konkuk.strhat.data.dto.response.ResponseSelfDiagnosisResultDto

interface SelfDiagnosisDataSource {
    suspend fun getSelfDiagnosisQuestionList(type: String): BaseResponse<List<ResponseSelfDiagnosisQuestionDto>>
    suspend fun getSelfDiagnosisResult(date: String, type: String): BaseResponse<ResponseSelfDiagnosisResultDto>
}