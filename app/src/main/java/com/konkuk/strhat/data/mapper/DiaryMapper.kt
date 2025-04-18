package com.konkuk.strhat.data.mapper

import com.konkuk.strhat.data.dto.response.ResponseDiaryExistenceDto
import com.konkuk.strhat.data.dto.response.ResponseSaveDiaryDto
import com.konkuk.strhat.data.dto.response.ResponseTotalDiaryDto
import com.konkuk.strhat.domain.entity.DiaryExistenceModel
import com.konkuk.strhat.domain.entity.DiaryFeedbackModel
import com.konkuk.strhat.domain.entity.TotalDiaryModel

fun ResponseSaveDiaryDto.toDiaryFeedbackModel() = DiaryFeedbackModel(
    summary = summary,
    positiveKeywords = positiveKeywords,
    negativeKeywords = negativeKeywords,
    stressReliefSuggestions = stressReliefSuggestions
)

fun ResponseTotalDiaryDto.toTotalDiaryModel() = TotalDiaryModel(
    content = content
)

fun ResponseDiaryExistenceDto.toDiaryExistenceModel() = DiaryExistenceModel(
    hasDiary = hasDiary,
    emotion = emotion,
    summary = summary
)