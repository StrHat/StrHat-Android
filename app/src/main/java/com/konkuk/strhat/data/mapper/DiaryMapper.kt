package com.konkuk.strhat.data.mapper

import com.konkuk.strhat.data.dto.response.ResponseSaveDiaryDto
import com.konkuk.strhat.data.dto.response.ResponseTotalDiaryDto
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