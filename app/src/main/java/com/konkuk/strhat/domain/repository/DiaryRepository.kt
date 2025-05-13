package com.konkuk.strhat.domain.repository

import com.konkuk.strhat.domain.entity.AddDiaryModel
import com.konkuk.strhat.domain.entity.DiaryExistenceModel
import com.konkuk.strhat.domain.entity.DiaryFeedbackModel
import com.konkuk.strhat.domain.entity.TotalDiaryModel

interface DiaryRepository {
    suspend fun postDiary(request: AddDiaryModel): Result<DiaryFeedbackModel>
    suspend fun getTotalDiary(date: String): Result<TotalDiaryModel>
    suspend fun getDiaryExistence(date: String): Result<DiaryExistenceModel>
    suspend fun getDiaryFeedback(date: String): Result<DiaryFeedbackModel>
}