package com.konkuk.strhat.data.di

import com.konkuk.strhat.data.service.DiaryService
import com.konkuk.strhat.data.service.HomeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun providesHomeService(retrofit: Retrofit): HomeService =
        retrofit.create(HomeService::class.java)

    @Provides
    @Singleton
    fun providesDiaryService(retrofit: Retrofit): DiaryService =
        retrofit.create(DiaryService::class.java)
}