package com.ravnnerdery.domain.repository

import com.ravnnerdery.domain.models.MarsPhoto
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun loadFromApiAndSetIntoDatabase(sol: String, rover: String, isRefreshing: Boolean)
    fun provideNoPhotosFoundState(): Flow<String>
    fun loadAllPhotosFromDatabase()
    fun provideEnlargedPhoto(id: Long): Flow<MarsPhoto>
    fun provideLoadingState(): Flow<Boolean>
    fun provideFilterNames(): Flow<List<String>>
    fun filterByCameraName(camera: String)
    fun providePhotosList(): Flow<List<MarsPhoto>>
    fun requestNewSol()
    fun clearFilter()
    fun provideRefreshState(): Flow<Boolean>
    fun refreshData()
}