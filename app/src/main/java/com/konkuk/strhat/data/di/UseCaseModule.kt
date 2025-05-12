package com.konkuk.strhat.data.di

import com.konkuk.strhat.domain.repository.AuthRepository
import com.konkuk.strhat.domain.repository.HomeRepository
import com.konkuk.strhat.domain.usecase.HomeUseCase
import com.konkuk.strhat.domain.usecase.KakaoLoginUseCase
import com.konkuk.strhat.domain.usecase.SignUpUseCase
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

    @Provides
    @Singleton
    fun providesKakaoLoginUseCase(
        authRepository: AuthRepository
    ): KakaoLoginUseCase = KakaoLoginUseCase(authRepository)

    @Provides
    @Singleton
    fun providesSignUpUseCase(
        authRepository: AuthRepository
    ): SignUpUseCase = SignUpUseCase(authRepository)
}