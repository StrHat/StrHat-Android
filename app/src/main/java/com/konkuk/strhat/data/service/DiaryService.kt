package com.konkuk.strhat.data.service

import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.request.RequestAddDiaryDto
import com.konkuk.strhat.data.dto.response.ResponseSaveDiaryDto
import com.konkuk.strhat.data.dto.response.ResponseTotalDiaryDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DiaryService {
    @POST("/api/v1/diary")
    suspend fun sendDiary(
        @Body request: RequestAddDiaryDto
    ): BaseResponse<ResponseSaveDiaryDto>

    @GET("/api/v1/diary")
    suspend fun getTotalDiary(
        @Query("date") date: String
    ): BaseResponse<ResponseTotalDiaryDto>
}