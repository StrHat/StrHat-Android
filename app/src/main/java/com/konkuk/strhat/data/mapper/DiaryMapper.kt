package com.konkuk.strhat.data.mapper

import com.konkuk.strhat.data.dto.request.RequestAddDiaryDto
import com.konkuk.strhat.data.dto.response.ResponseDiaryExistenceDto
import com.konkuk.strhat.data.dto.response.ResponseSaveDiaryDto
import com.konkuk.strhat.data.dto.response.ResponseTotalDiaryDto
import com.konkuk.strhat.domain.entity.AddDiaryModel
import com.konkuk.strhat.domain.entity.DiaryExistenceModel
import com.konkuk.strhat.domain.entity.DiaryFeedbackModel
import com.konkuk.strhat.domain.entity.TotalDiaryModel

fun ResponseSaveDiaryDto.toDiaryFeedbackModel() = DiaryFeedbackModel(
    summary = summary,
    positiveKeywords = positiveKeywords,
    negativeKeywords = negativeKeywords,
    stressReliefSuggestions = stressReliefSuggestions,
    diaryId = diaryId
)

fun ResponseTotalDiaryDto.toTotalDiaryModel() = TotalDiaryModel(
    content = content,
    diaryId = diaryId
)

fun ResponseDiaryExistenceDto.toDiaryExistenceModel() = DiaryExistenceModel(
    hasDiary = hasDiary,
    emotion = emotion,
    summary = summary,
    diaryId = diaryId
)

fun AddDiaryModel.toAddDiaryDto() = RequestAddDiaryDto(
    date = date,
    emotion = emotion,
    content = content
)