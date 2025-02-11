package com.konkuk.strhat.data.datasource

import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.response.ResponseHomeDto

interface HomeDataSource {
    suspend fun getHomeData(): BaseResponse<ResponseHomeDto>
}