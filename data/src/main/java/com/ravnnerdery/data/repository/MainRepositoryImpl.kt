package com.ravnnerdery.data.repository

import android.util.Log
import com.ravnnerdery.data.database.DatabaseDao
import com.ravnnerdery.data.database.models.MarsPhotoEntity
import com.ravnnerdery.data.networking.NasaApiService
import com.ravnnerdery.data.networking.models.MarsPhotoContainerResponse
import com.ravnnerdery.domain.models.MarsPhoto
import com.ravnnerdery.domain.repository.MainRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainRepositoryImpl @Inject constructor(
    private val photosDao: DatabaseDao,
    private val photosApi: NasaApiService,
) : MainRepository {
    private var repositoryJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + repositoryJob)
    private var allPhotosContainer = MutableStateFlow<List<MarsPhoto>>(emptyList())
    private lateinit var allPhotosBackUp: List<MarsPhoto>
    private var filterNames = MutableStateFlow<List<String>>(emptyList())
    private var enlargedPhoto = MutableStateFlow(MarsPhoto(0,0,"null","null","null"))

    init {
        loadFromApiAndSetIntoDatabase()
    }

    override fun filterByCameraName(camera: String) {
        allPhotosContainer.value = allPhotosBackUp.filter {
            it.camera == camera
        }
    }

    override fun providePhotosList(): MutableStateFlow<List<MarsPhoto>> = allPhotosContainer

    override fun provideFilterNames(): MutableStateFlow<List<String>> = filterNames

    override fun provideEnlargedPhoto(id: Long): MutableStateFlow<MarsPhoto> {
        uiScope.launch(Dispatchers.IO){
            val tempContainer = photosDao.getEnlargedPhoto(id).mapToDomainModel()
            enlargedPhoto.value = tempContainer
        }
        return enlargedPhoto
    }


    override fun loadAllPhotosFromDatabase() {
        uiScope.launch(Dispatchers.IO) {
            val latestPhotos = photosDao.getPhotos()
            filterNames.value = photosDao.getAllCameraNames()
            val convertedLatestPhotos: MutableList<MarsPhoto> = mutableListOf()
            latestPhotos.forEach {
                convertedLatestPhotos.add(it.mapToDomainModel())
            }
            allPhotosContainer.value = convertedLatestPhotos
            allPhotosBackUp = convertedLatestPhotos
        }
    }


    override fun loadFromApiAndSetIntoDatabase() {
        uiScope.launch(Dispatchers.IO){
            photosDao.truncateDatabase()
            loadFromApiAndSetIntoDatabase()
        }
        photosApi.getPhotos("1002").enqueue(object : Callback<MarsPhotoContainerResponse> {
            override fun onResponse(
                call: Call<MarsPhotoContainerResponse>,
                response: Response<MarsPhotoContainerResponse>
            ) {
                response.body()?.photos?.forEach { elm ->
                    addPhotoToDatabase(
                        elm.id,
                        elm.sol,
                        elm.rover.name,
                        elm.camera.full_name,
                        elm.img_src
                    )
                }
                loadAllPhotosFromDatabase()
            }

            override fun onFailure(call: Call<MarsPhotoContainerResponse>, t: Throwable) {
                Log.v("Network Response: ", t.message.toString())
            }
        })

    }


    private fun addPhotoToDatabase(
        id: Long,
        sol: Long,
        rover: String,
        camera: String,
        imgUrl: String
    ) {
        uiScope.launch(Dispatchers.IO) {
            val newPhoto = MarsPhotoEntity(
                id = id,
                sol = sol,
                rover = rover,
                camera = camera,
                imgUrl = imgUrl
            )
            photosDao.insertPhoto(newPhoto)
        }
    }
}
