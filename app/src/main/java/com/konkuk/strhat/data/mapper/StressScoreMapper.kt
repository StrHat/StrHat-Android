package com.konkuk.strhat.data.mapper

import com.konkuk.strhat.data.dto.response.ResponseStressScoreDto
import com.konkuk.strhat.data.dto.response.ResponseWeeklyStressScoreDto
import com.konkuk.strhat.domain.entity.StressScoreModel
import com.konkuk.strhat.domain.entity.WeeklyStressScoreModel

fun ResponseStressScoreDto.toStressScoreModel() = StressScoreModel(
    nickname = nickname,
    stressScore = stressScore,
    level = level,
    analysis = analysis,
    stressScoreDate = stressScoreDate
)

fun ResponseWeeklyStressScoreDto.toWeeklyStressScoreModel() = WeeklyStressScoreModel(
    nickname = nickname,
    weeklySummary = weeklySummary,
    stressLevels = stressLevels,
    emotionLevels = emotionLevels,
    startDate = startDate,
    endDate = endDate
)