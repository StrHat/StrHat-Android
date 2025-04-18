package com.konkuk.strhat.data.datasourceimpl

import com.konkuk.strhat.data.datasource.DiaryDataSource
import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.request.RequestAddDiaryDto
import com.konkuk.strhat.data.dto.response.ResponseDiaryExistenceDto
import com.konkuk.strhat.data.dto.response.ResponseSaveDiaryDto
import com.konkuk.strhat.data.dto.response.ResponseTotalDiaryDto
import com.konkuk.strhat.data.service.DiaryService
import javax.inject.Inject

class DiaryDataSourceImpl @Inject constructor(
    private val diaryService: DiaryService
) : DiaryDataSource {
    override suspend fun postDiary(request: RequestAddDiaryDto): BaseResponse<ResponseSaveDiaryDto> =
        diaryService.sendDiary(request)

    override suspend fun getTotalDiary(date: String): BaseResponse<ResponseTotalDiaryDto> =
        diaryService.getTotalDiary(date)

    override suspend fun getDiaryExistence(date: String): BaseResponse<ResponseDiaryExistenceDto> =
        diaryService.getDiaryExistence(date)
}