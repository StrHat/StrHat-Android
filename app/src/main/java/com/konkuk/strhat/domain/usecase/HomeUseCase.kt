package com.konkuk.strhat.domain.usecase

import com.konkuk.strhat.domain.entity.HomeModel
import com.konkuk.strhat.domain.repository.HomeRepository
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(): Result<HomeModel> =
        homeRepository.getHomeData()
}