package com.konkuk.strhat.data.di

import com.konkuk.strhat.data.repositoryimpl.HomeRepositoryImpl
import com.konkuk.strhat.domain.repository.HomeRepository
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
}