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
    fun providePhotolistUseCase(
        providePhotoListUseCaseImpl: ProvidePhotoListUseCaseImpl
    ): ProvidePhotoListUseCase {
        return providePhotoListUseCaseImpl
    }

    @Provides
    @Singleton
    fun provideEnlargedPhotoUseCase(
        provideEnlargedPhotoUseCaseImpl: ProvideEnlargedPhotoUseCaseImpl
    ): ProvideEnlargedPhotoUseCase {
        return provideEnlargedPhotoUseCaseImpl
    }

    @Provides
    @Singleton
    fun provideFilterNamesUseCase(
        provideFilterNamesUseCaseImpl: ProvideFilterNamesUseCaseImpl
    ): ProvideFilterNamesUseCase {
        return provideFilterNamesUseCaseImpl
    }

    @Provides
    @Singleton
    fun filterByCameraNameUseCase(
        filterByCameraNameUseCaseImpl: FilterByCameraNameUseCaseImpl
    ): FilterByCameraNameUseCase {
        return filterByCameraNameUseCaseImpl
    }

    @Provides
    @Singleton
    fun clearFilterUseCase(
        clearFilterUseCaseImpl: ClearFilterUseCaseImpl
    ): ClearFilterUseCase {
        return clearFilterUseCaseImpl
    }

    @Provides
    @Singleton
    fun loadFromApiAndSetIntoDatabaseUseCase(
        loadFromApiAndSetIntoDatabaseUseCaseImpl: LoadFromApiAndSetIntoDatabaseUseCaseImpl
    ): LoadFromApiAndSetIntoDatabaseUseCase {
        return loadFromApiAndSetIntoDatabaseUseCaseImpl
    }

    @Provides
    @Singleton
    fun requestNewSolUseCase(
        requestNewSolUseCaseImpl: RequestNewSolUseCaseImpl
    ): RequestNewSolUseCase {
        return requestNewSolUseCaseImpl
    }

    @Provides
    @Singleton
    fun provideLoadingStateUseCase(
        provideLoadingStateUseCaseImpl: ProvideLoadingStateUseCaseImpl
    ): ProvideLoadingStateUseCase {
        return provideLoadingStateUseCaseImpl
    }

    @Provides
    @Singleton
    fun provideNoPhotosFoundStateUseCase(
        provideNoPhotosFoundStateUseCaseImpl: ProvideNoPhotosFoundStateUseCaseImpl
    ): ProvideNoPhotosFoundStateUseCase {
        return provideNoPhotosFoundStateUseCaseImpl
    }

    @Provides
    @Singleton
    fun provideRefreshStateUseCase(
        provideRefreshStateUseCaseImpl: ProvideRefreshStateUseCaseImpl
    ): ProvideRefreshStateUseCase {
        return provideRefreshStateUseCaseImpl
    }

    @Provides
    @Singleton
    fun refreshDataUseCase(
        refreshDataUseCaseImpl: RefreshDataUseCaseImpl
    ): RefreshDataUseCase {
        return refreshDataUseCaseImpl
    }

}