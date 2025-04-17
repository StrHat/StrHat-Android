package com.konkuk.strhat.data.repositoryimpl

import com.konkuk.strhat.data.datasource.DiaryDataSource
import com.konkuk.strhat.data.dto.request.RequestAddDiaryDto
import com.konkuk.strhat.data.mapper.toDiaryFeedbackModel
import com.konkuk.strhat.domain.entity.DiaryFeedbackModel
import com.konkuk.strhat.domain.repository.DiaryRepository
import javax.inject.Inject

class DiaryRepositoryImpl @Inject constructor(
    private val diaryDataSource: DiaryDataSource
) : DiaryRepository {
    override suspend fun postDiary(request: RequestAddDiaryDto): Result<DiaryFeedbackModel> =
        runCatching {
            val response = diaryDataSource.postDiary(request)
            response.response.toDiaryFeedbackModel()
        }
}