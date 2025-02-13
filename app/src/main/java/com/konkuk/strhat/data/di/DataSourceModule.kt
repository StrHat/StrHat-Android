package com.konkuk.strhat.data.di

import com.konkuk.strhat.data.datasource.HomeDataSource
import com.konkuk.strhat.data.datasourceimpl.HomeDataSourceImpl
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
}