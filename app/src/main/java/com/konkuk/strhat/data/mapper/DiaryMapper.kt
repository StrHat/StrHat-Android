package com.konkuk.strhat.data.mapper

import com.konkuk.strhat.data.dto.response.ResponseSaveDiaryDto
import com.konkuk.strhat.domain.entity.DiaryFeedbackModel

fun ResponseSaveDiaryDto.toDiaryFeedbackModel() = DiaryFeedbackModel(
    summary = summary,
    positiveKeywords = positiveKeywords,
    negativeKeywords = negativeKeywords,
    stressReliefSuggestions = stressReliefSuggestions
)