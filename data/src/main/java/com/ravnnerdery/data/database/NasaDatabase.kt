package com.ravnnerdery.data.database

import androidx.room.*
import com.ravnnerdery.data.database.models.MarsPhotoEntity

@Database(entities = [MarsPhotoEntity::class], version = 8, exportSchema = false)
abstract class NasaDatabase : RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao
}
