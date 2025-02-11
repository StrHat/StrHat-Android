package com.konkuk.strhat.domain.repository

import com.konkuk.strhat.domain.entity.HomeModel

interface HomeRepository {
    suspend fun getHomeData() : Result<HomeModel>
}