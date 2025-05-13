package com.konkuk.strhat.data.datasource

import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.response.ResponseStressScoreDto

interface StressScoreDataSource {
    suspend fun getStressScore(date: String): BaseResponse<ResponseStressScoreDto>
}