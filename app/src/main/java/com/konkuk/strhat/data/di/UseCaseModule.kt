package com.konkuk.strhat.data.di

import com.konkuk.strhat.domain.repository.HomeRepository
import com.konkuk.strhat.domain.usecase.HomeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun providesHomeUseCase(
        homeRepository: HomeRepository
    ): HomeUseCase = HomeUseCase(homeRepository)
}