package com.konkuk.strhat.data.repositoryimpl

import com.konkuk.strhat.data.datasource.StressScoreDataSource
import com.konkuk.strhat.data.mapper.toStressScoreModel
import com.konkuk.strhat.data.mapper.toWeeklyStressScoreModel
import com.konkuk.strhat.domain.entity.StressScoreModel
import com.konkuk.strhat.domain.entity.WeeklyStressScoreModel
import com.konkuk.strhat.domain.repository.StressScoreRepository
import javax.inject.Inject

class StressScoreRepositoryImpl @Inject constructor(
    private val stressScoreDataSource: StressScoreDataSource
) : StressScoreRepository {
    override suspend fun getStressScore(date: String): Result<StressScoreModel> =
        runCatching {
            val response = stressScoreDataSource.getStressScore(date).response
            response.toStressScoreModel()
        }

    override suspend fun getWeeklyStressScore(date: String): Result<WeeklyStressScoreModel> =
        runCatching {
            val response = stressScoreDataSource.getWeeklyStressScore(date).response
            response.toWeeklyStressScoreModel()
        }
}