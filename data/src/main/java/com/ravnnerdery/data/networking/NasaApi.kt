package com.ravnnerdery.data.networking

import com.ravnnerdery.data.networking.models.MarsPhotoContainerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NasaApiService {
    // "photos?sol=1000&api_key=nRM7JW3QySKpFgkcky6E3npsMNNGv9YDWvkWOPue"
    @GET("photos?sol=1000&api_key=nRM7JW3QySKpFgkcky6E3npsMNNGv9YDWvkWOPue")
    fun getPhotos(@Query("sol") sol: String, @Query("api_key") api_key: String = "nRM7JW3QySKpFgkcky6E3npsMNNGv9YDWvkWOPue"): Call<MarsPhotoContainerResponse>
}

