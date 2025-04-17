package com.konkuk.strhat.data.datasourceimpl

import com.konkuk.strhat.data.datasource.DiaryDataSource
import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.request.RequestAddDiaryDto
import com.konkuk.strhat.data.dto.response.ResponseSaveDiaryDto
import com.konkuk.strhat.data.service.DiaryService
import javax.inject.Inject

class DiaryDataSourceImpl @Inject constructor(
    private val diaryService: DiaryService
) : DiaryDataSource {
    override suspend fun postDiary(request: RequestAddDiaryDto): BaseResponse<ResponseSaveDiaryDto> =
        diaryService.sendDiary(request)
}