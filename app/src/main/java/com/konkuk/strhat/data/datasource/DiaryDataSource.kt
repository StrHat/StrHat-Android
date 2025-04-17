package com.konkuk.strhat.data.datasource

import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.request.RequestAddDiaryDto
import com.konkuk.strhat.data.dto.response.ResponseSaveDiaryDto

interface DiaryDataSource {
    suspend fun postDiary(request: RequestAddDiaryDto): BaseResponse<ResponseSaveDiaryDto>
}