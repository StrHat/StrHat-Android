package com.konkuk.strhat.data.mapper

import com.konkuk.strhat.data.dto.response.ResponseHomeDto
import com.konkuk.strhat.domain.entity.HomeModel

fun ResponseHomeDto.toHomeModel() = HomeModel(
    hasDiary = hasDiary,
    nickname = nickname,
    emotion = emotion ?: 1,
    positiveEmotions = positiveEmotions ?: listOf("", "", ""),
    stressReliefSuggestion = stressReliefSuggestion ?: "",
    stressScore = stressScore ?: 0,
    stressLevel = stressLevel ?: ""
)