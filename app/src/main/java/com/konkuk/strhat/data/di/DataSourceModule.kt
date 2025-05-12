package com.konkuk.strhat.data.di

import com.konkuk.strhat.data.datasource.AuthDataSource
import com.konkuk.strhat.data.datasource.ChatDataSource
import com.konkuk.strhat.data.datasource.DiaryDataSource
import com.konkuk.strhat.data.datasource.HomeDataSource
import com.konkuk.strhat.data.datasource.StressScoreDataSource
import com.konkuk.strhat.data.datasourceimpl.AuthDataSourceImpl
import com.konkuk.strhat.data.datasourceimpl.ChatDataSourceImpl
import com.konkuk.strhat.data.datasourceimpl.DiaryDataSourceImpl
import com.konkuk.strhat.data.datasourceimpl.HomeDataSourceImpl
import com.konkuk.strhat.data.datasourceimpl.StressScoreDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindsHomeDataSource(homeDataSourceImpl: HomeDataSourceImpl): HomeDataSource

    @Binds
    @Singleton
    abstract fun bindsDiaryDataSource(diaryDataSourceImpl: DiaryDataSourceImpl): DiaryDataSource

    @Binds
    @Singleton
    abstract fun bindsAuthDataSource(authDataSourceImpl: AuthDataSourceImpl): AuthDataSource

    @Binds
    @Singleton
    abstract fun bindsStressScoreDataSource(stressScoreDataSourceImpl: StressScoreDataSourceImpl): StressScoreDataSource

    @Binds
    @Singleton
    abstract fun bindsChatDataSource(chatDataSourceImpl: ChatDataSourceImpl): ChatDataSource
}