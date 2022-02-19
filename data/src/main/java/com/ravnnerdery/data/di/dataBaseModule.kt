package com.ravnnerdery.data.di

import android.content.Context
import androidx.room.Room
import com.ravnnerdery.data.database.DatabaseDao
import com.ravnnerdery.data.database.NasaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val NASA_DATABASE = "Nasa_dbase2"

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabaseDao(photosDatabase: NasaDatabase): DatabaseDao {
        return photosDatabase.databaseDao()
    }

    @Provides
    @Singleton
    fun provideNasaDatabase(@ApplicationContext appContext: Context): NasaDatabase {
        return Room.databaseBuilder(
            appContext,
            NasaDatabase::class.java,
            NASA_DATABASE
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}