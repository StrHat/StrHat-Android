package com.konkuk.strhat.data.di

import com.konkuk.strhat.data.repositoryimpl.ChatRepositoryImpl
import com.konkuk.strhat.data.repositoryimpl.DiaryRepositoryImpl
import com.konkuk.strhat.data.repositoryimpl.HomeRepositoryImpl
import com.konkuk.strhat.data.repositoryimpl.StressScoreRepositoryImpl
import com.konkuk.strhat.domain.repository.ChatRepository
import com.konkuk.strhat.domain.repository.DiaryRepository
import com.konkuk.strhat.domain.repository.HomeRepository
import com.konkuk.strhat.domain.repository.StressScoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

    @Binds
    @Singleton
    abstract fun bindsDiaryRepository(diaryRepositoryImpl: DiaryRepositoryImpl): DiaryRepository

    @Binds
    @Singleton
    abstract fun bindsStressScoreRepository(stressScoreRepositoryImpl: StressScoreRepositoryImpl): StressScoreRepository

    @Binds
    @Singleton
    abstract fun bindsChatRepository(chatRepositoryImpl: ChatRepositoryImpl): ChatRepository
}