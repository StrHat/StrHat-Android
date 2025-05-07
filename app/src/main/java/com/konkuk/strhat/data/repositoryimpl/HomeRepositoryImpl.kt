package com.konkuk.strhat.data.repositoryimpl

import com.konkuk.strhat.data.datasource.HomeDataSource
import com.konkuk.strhat.data.mapper.toHomeModel
import com.konkuk.strhat.domain.entity.HomeModel
import com.konkuk.strhat.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource
) : HomeRepository {
    override suspend fun getHomeData(): Result<HomeModel> =
        runCatching {
            val response = homeDataSource.getHomeData()
            response.response.toHomeModel() ?: throw Exception("Response data is null")
        }
}