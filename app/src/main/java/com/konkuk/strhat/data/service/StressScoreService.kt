package com.konkuk.strhat.data.service

import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.response.ResponseStressScoreDto
import com.konkuk.strhat.data.dto.response.ResponseWeeklyStressScoreDto
import retrofit2.http.GET
import retrofit2.http.Query

interface StressScoreService {
    @GET("api/v1/stress-score/daily")
    suspend fun getStressScore(
        @Query("date") date: String
    ): BaseResponse<ResponseStressScoreDto>

    @GET("api/v1/stress-score/weekly")
    suspend fun getWeeklyStressScore(
        @Query("date") date: String
    ): BaseResponse<ResponseWeeklyStressScoreDto>
}