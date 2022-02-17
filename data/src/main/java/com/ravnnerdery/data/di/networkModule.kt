package com.ravnnerdery.data.di

import com.ravnnerdery.data.networking.NasaApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {
    private const val BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/"

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun getPhotosAPI(retrofit: Retrofit): NasaApiService {
        val retrofitService: NasaApiService by lazy {
            retrofit.create(NasaApiService::class.java)
        }
        return retrofitService
    }

}