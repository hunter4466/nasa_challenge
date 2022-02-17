package com.ravnnerdery.data.di

import com.ravnnerdery.data.useCases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataUseCasesModule {
    @Provides
    @Singleton
    fun providePhotolistUseCase (
        providePhotoListUseCaseImpl: ProvidePhotoListUseCaseImpl
    ): ProvidePhotoListUseCase {
        return providePhotoListUseCaseImpl
    }

    @Provides
    @Singleton
    fun provideEnlargedPhotoUseCase (
        provideEnlargedPhotoUseCaseImpl: ProvideEnlargedPhotoUseCaseImpl
    ) : ProvideEnlargedPhotoUseCase {
        return provideEnlargedPhotoUseCaseImpl
    }

    @Provides
    @Singleton
    fun provideFilterNamesUseCase (
        provideFilterNamesUseCaseImpl: ProvideFilterNamesUseCaseImpl
    ) : ProvideFilterNamesUseCase {
        return provideFilterNamesUseCaseImpl
    }

    @Provides
    @Singleton
    fun filterByCameraName (
        filterByCameraNameUseCaseImpl: FilterByCameraNameUseCaseImpl
    ) : FilterByCameraNameUseCase {
        return filterByCameraNameUseCaseImpl
    }
}