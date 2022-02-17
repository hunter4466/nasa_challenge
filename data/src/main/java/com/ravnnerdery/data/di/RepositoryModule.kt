package com.ravnnerdery.data.di

import com.ravnnerdery.data.repository.MainRepositoryImpl
import com.ravnnerdery.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideMainRepositoryImpl (repository: MainRepositoryImpl): MainRepository {
        return repository
    }
}