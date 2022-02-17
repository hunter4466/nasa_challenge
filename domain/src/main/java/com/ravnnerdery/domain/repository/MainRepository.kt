package com.ravnnerdery.domain.repository

import com.ravnnerdery.domain.models.MarsPhoto
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun loadAllPhotosFromDatabase()
    fun loadFromApiAndSetIntoDatabase()
    fun providePhotosList(): Flow<List<MarsPhoto>>
    fun provideEnlargedPhoto(id: Long): Flow<MarsPhoto>
    fun provideFilterNames(): Flow<List<String>>
    fun filterByCameraName(camera: String)
}