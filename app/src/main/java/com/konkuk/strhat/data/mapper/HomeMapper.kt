package com.konkuk.strhat.data.mapper

import com.konkuk.strhat.data.dto.response.ResponseHomeDto
import com.konkuk.strhat.domain.entity.HomeModel

fun ResponseHomeDto.toHomeModel() = HomeModel(
    nickname = nickname,
    emotion = emotion,
    positiveEmotions = positiveEmotions,
    stressReliefSuggestion = stressReliefSuggestion,
    stressScore = stressScore,
    stressLevel = stressLevel
)