package com.konkuk.strhat.data.service

import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.request.RequestSelfDiagnosisDto
import com.konkuk.strhat.data.dto.response.ResponseSelfDiagnosisQuestionDto
import com.konkuk.strhat.data.dto.response.ResponseSelfDiagnosisResultDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SelfDiagnosisService {
    @GET("/api/v1/self-diagnoses")
    suspend fun getSelfDiagnosisQuestionList(
        @Query("type") type: String
    ): BaseResponse<List<ResponseSelfDiagnosisQuestionDto>>

    @GET("/api/v1/self-diagnoses/result")
    suspend fun getSelfDiagnosisResult(
        @Query("date") date: String,
        @Query("type") type: String
    ): BaseResponse<ResponseSelfDiagnosisResultDto>

    @POST("/api/v1/self-diagnoses")
    suspend fun postSelfDiagnosis(
        @Body request: RequestSelfDiagnosisDto
    ): BaseResponse<Unit>
}