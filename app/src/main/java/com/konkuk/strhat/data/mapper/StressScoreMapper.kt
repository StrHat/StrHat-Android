package com.konkuk.strhat.data.mapper

import com.konkuk.strhat.data.dto.response.ResponseStressScoreDto
import com.konkuk.strhat.domain.entity.StressScoreModel

fun ResponseStressScoreDto.toStressScoreModel() = StressScoreModel(
    nickname = nickname,
    stressScore = stressScore,
    level = level,
    analysis = analysis,
    stressScoreDate = stressScoreDate
)