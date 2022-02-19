package com.ravnnerdery.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ravnnerdery.data.database.models.MarsPhotoEntity

@Dao
interface DatabaseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPhoto(marsPhotoEntity: MarsPhotoEntity)

    @Query("DELETE FROM photo_table")
    fun truncateDatabase()

    @Query("SELECT * from photo_table")
    fun getPhotos(): List<MarsPhotoEntity>

    @Query("SELECT * from photo_table WHERE id = :key")
    fun getEnlargedPhoto(key: Long): MarsPhotoEntity

    @Query("SELECT distinct cameraCode FROM photo_table")
    fun getAllCameraNames(): List<String>

}

