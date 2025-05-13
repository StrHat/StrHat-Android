package com.konkuk.strhat.domain.repository

import com.konkuk.strhat.domain.entity.StressScoreModel

interface StressScoreRepository {
    suspend fun getStressScore(date: String): Result<StressScoreModel>
}