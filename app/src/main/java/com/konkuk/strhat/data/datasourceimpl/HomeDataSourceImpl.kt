package com.konkuk.strhat.data.datasourceimpl

import com.konkuk.strhat.data.datasource.HomeDataSource
import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.response.ResponseHomeDto
import com.konkuk.strhat.data.service.HomeService
import javax.inject.Inject

class HomeDataSourceImpl @Inject constructor(
    private val homeService: HomeService
) : HomeDataSource {
    override suspend fun getHomeData(): BaseResponse<ResponseHomeDto> =
        homeService.getHomeData()
}