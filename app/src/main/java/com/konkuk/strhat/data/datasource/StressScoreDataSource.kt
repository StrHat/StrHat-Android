package com.konkuk.strhat.data.datasource

import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.response.ResponseStressScoreDto
import com.konkuk.strhat.data.dto.response.ResponseWeeklyStressScoreDto

interface StressScoreDataSource {
    suspend fun getStressScore(date: String): BaseResponse<ResponseStressScoreDto>
    suspend fun getWeeklyStressScore(date: String): BaseResponse<ResponseWeeklyStressScoreDto>
}