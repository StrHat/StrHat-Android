package com.konkuk.strhat.domain.repository

import com.konkuk.strhat.domain.entity.StressScoreModel
import com.konkuk.strhat.domain.entity.WeeklyStressScoreModel

interface StressScoreRepository {
    suspend fun getStressScore(date: String): Result<StressScoreModel>
    suspend fun getWeeklyStressScore(date: String): Result<WeeklyStressScoreModel>
}