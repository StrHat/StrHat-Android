package com.konkuk.strhat.domain.repository

import com.konkuk.strhat.data.dto.request.RequestAddDiaryDto
import com.konkuk.strhat.domain.entity.DiaryFeedbackModel

interface DiaryRepository {
    suspend fun postDiary(request: RequestAddDiaryDto): Result<DiaryFeedbackModel>
}