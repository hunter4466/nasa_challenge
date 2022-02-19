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
    private lateinit var allPhotosBackUp: List<MarsPhoto>
    private var refreshSolMemory: String = ""
    private var refreshRoverMemory: String = ""
    private var repositoryJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + repositoryJob)
    private var allPhotosContainer = MutableStateFlow<List<MarsPhoto>>(emptyList())
    private var filterNames = MutableStateFlow<List<String>>(emptyList())
    private var enlargedPhoto = MutableStateFlow(MarsPhoto(0, 0, "null", "null", "null", "null"))
    private var loadingState = MutableStateFlow(false)
    private var noPhotosFoundState = MutableStateFlow("")
    private var refreshState = MutableStateFlow(false)


    override fun provideNoPhotosFoundState(): MutableStateFlow<String> = noPhotosFoundState

    override fun providePhotosList(): MutableStateFlow<List<MarsPhoto>> = allPhotosContainer

    override fun provideFilterNames(): MutableStateFlow<List<String>> = filterNames

    override fun provideLoadingState(): MutableStateFlow<Boolean> = loadingState

    override fun provideRefreshState(): MutableStateFlow<Boolean> = refreshState


    override fun requestNewSol() {
        allPhotosContainer.value = emptyList()
    }

    override fun clearFilter() {
        allPhotosContainer.value = allPhotosBackUp
    }

    override fun filterByCameraName(camera: String) {
        allPhotosContainer.value = allPhotosBackUp.filter {
            it.cameraCode == camera
        }
    }

    override fun refreshData() {
        loadFromApiAndSetIntoDatabase(refreshSolMemory, refreshRoverMemory, true)
    }

    override fun provideEnlargedPhoto(id: Long): MutableStateFlow<MarsPhoto> {
        loadingState.value = true
        uiScope.launch(Dispatchers.IO) {
            val tempContainer = photosDao.getEnlargedPhoto(id).mapToDomainModel()
            enlargedPhoto.value = tempContainer
            loadingState.value = false
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
            loadingState.value = false
            refreshState.value = false
        }
    }


    override fun loadFromApiAndSetIntoDatabase(sol: String, rover: String, isRefreshing: Boolean) {
        refreshSolMemory = sol
        refreshRoverMemory = rover
        if (isRefreshing) {
            refreshState.value = true
        } else {
            loadingState.value = true
        }
        uiScope.launch(Dispatchers.IO) {
            photosDao.truncateDatabase()
        }
        photosApi.getPhotos(rover, sol).enqueue(object : Callback<MarsPhotoContainerResponse> {
            override fun onResponse(
                call: Call<MarsPhotoContainerResponse>,
                response: Response<MarsPhotoContainerResponse>
            ) {
                if (response.body()?.photos?.isEmpty() == true) {
                    noPhotosFoundState.value = "No Photos Found with this sol, please try again"
                }
                response.body()?.photos?.forEach { elm ->
                    addPhotoToDatabase(
                        elm.id,
                        elm.sol,
                        elm.rover.name,
                        elm.camera.full_name,
                        elm.camera.name,
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
        cameraCode: String,
        imgUrl: String
    ) {
        uiScope.launch(Dispatchers.IO) {
            val newPhoto = MarsPhotoEntity(
                id = id,
                sol = sol,
                rover = rover,
                camera = camera,
                cameraCode = cameraCode,
                imgUrl = imgUrl
            )
            photosDao.insertPhoto(newPhoto)
        }
    }
}
