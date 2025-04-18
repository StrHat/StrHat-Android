package com.konkuk.strhat.data.datasource

import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.request.RequestAddDiaryDto
import com.konkuk.strhat.data.dto.response.ResponseDiaryExistenceDto
import com.konkuk.strhat.data.dto.response.ResponseSaveDiaryDto
import com.konkuk.strhat.data.dto.response.ResponseTotalDiaryDto

interface DiaryDataSource {
    suspend fun postDiary(request: RequestAddDiaryDto): BaseResponse<ResponseSaveDiaryDto>
    suspend fun getTotalDiary(date: String): BaseResponse<ResponseTotalDiaryDto>
    suspend fun getDiaryExistence(date: String): BaseResponse<ResponseDiaryExistenceDto>
    suspend fun getDiaryFeedback(date: String): BaseResponse<ResponseSaveDiaryDto>
}