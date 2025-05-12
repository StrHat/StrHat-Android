package com.konkuk.strhat.data.repositoryimpl

import com.konkuk.strhat.data.datasource.DiaryDataSource
import com.konkuk.strhat.data.dto.request.RequestAddDiaryDto
import com.konkuk.strhat.data.mapper.toDiaryExistenceModel
import com.konkuk.strhat.data.mapper.toDiaryFeedbackModel
import com.konkuk.strhat.data.mapper.toTotalDiaryModel
import com.konkuk.strhat.domain.entity.DiaryExistenceModel
import com.konkuk.strhat.domain.entity.DiaryFeedbackModel
import com.konkuk.strhat.domain.entity.TotalDiaryModel
import com.konkuk.strhat.domain.repository.DiaryRepository
import javax.inject.Inject

class DiaryRepositoryImpl @Inject constructor(
    private val diaryDataSource: DiaryDataSource
) : DiaryRepository {
    override suspend fun postDiary(request: RequestAddDiaryDto): Result<DiaryFeedbackModel> =
        runCatching {
            val response = diaryDataSource.postDiary(request).response
            response.toDiaryFeedbackModel()
        }

    override suspend fun getTotalDiary(date: String): Result<TotalDiaryModel> =
        runCatching {
            val response = diaryDataSource.getTotalDiary(date).response
            response.toTotalDiaryModel()
        }

    override suspend fun getDiaryExistence(date: String): Result<DiaryExistenceModel> =
        runCatching {
            val response = diaryDataSource.getDiaryExistence(date).response
            response.toDiaryExistenceModel()
        }

    override suspend fun getDiaryFeedback(date: String): Result<DiaryFeedbackModel> =
        runCatching {
            val response = diaryDataSource.getDiaryFeedback(date).response
            response.toDiaryFeedbackModel()
        }
}