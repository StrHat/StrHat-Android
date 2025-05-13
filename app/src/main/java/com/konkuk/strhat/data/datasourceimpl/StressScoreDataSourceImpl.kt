package com.konkuk.strhat.data.datasourceimpl

import com.konkuk.strhat.data.datasource.StressScoreDataSource
import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.response.ResponseStressScoreDto
import com.konkuk.strhat.data.service.StressScoreService
import javax.inject.Inject

class StressScoreDataSourceImpl @Inject constructor(
    private val stressScoreService: StressScoreService
) : StressScoreDataSource {
    override suspend fun getStressScore(date: String): BaseResponse<ResponseStressScoreDto> =
        stressScoreService.getStressScore(date)
}