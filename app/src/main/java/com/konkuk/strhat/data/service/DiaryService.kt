package com.konkuk.strhat.data.service

import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.request.RequestAddDiaryDto
import com.konkuk.strhat.data.dto.response.ResponseSaveDiaryDto
import retrofit2.http.Body
import retrofit2.http.POST

interface DiaryService {
    @POST("/api/v1/diary")
    suspend fun sendDiary(
        @Body request: RequestAddDiaryDto
    ): BaseResponse<ResponseSaveDiaryDto>
}