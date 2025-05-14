package com.konkuk.strhat.data.service

import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.response.ResponseSelfDiagnosisQuestionDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SelfDiagnosisService {
    @GET("/api/v1/self-diagnoses")
    suspend fun getSelfDiagnosisQuestionList(
        @Query("type") type: String
    ): BaseResponse<List<ResponseSelfDiagnosisQuestionDto>>
}