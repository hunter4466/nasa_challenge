package com.ravnnerdery.data.di

import com.ravnnerdery.data.repository.MainRepositoryImpl
import com.ravnnerdery.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Provides
    @Singleton
    fun provideMainRepositoryImpl(repository: MainRepositoryImpl): MainRepository {
        return repository
    }
}