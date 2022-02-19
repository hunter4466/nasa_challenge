package com.ravnnerdery.nasa_challenge.viewModels

import androidx.lifecycle.ViewModel
import com.ravnnerdery.data.useCases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    providePhotoListUseCase: ProvidePhotoListUseCase,
    provideFilterNamesUseCase: ProvideFilterNamesUseCase,
    provideLoadingStateUseCase: ProvideLoadingStateUseCase,
    provideNoPhotosFoundStateUseCase: ProvideNoPhotosFoundStateUseCase,
    provideRefreshStateUseCase: ProvideRefreshStateUseCase,
    private val refreshDataUseCase: RefreshDataUseCase,
    private val filterByCameraNameUseCase: FilterByCameraNameUseCase,
    private val clearFilterUseCase: ClearFilterUseCase,
    private val loadFromApiAndSetIntoDatabaseUseCase: LoadFromApiAndSetIntoDatabaseUseCase,
    private val requestNewSolUseCase: RequestNewSolUseCase,
) : ViewModel() {
    val observablePhotoList = providePhotoListUseCase.execute()
    val observableFilterList = provideFilterNamesUseCase.execute()
    val observableLoadingState = provideLoadingStateUseCase.execute()
    val observableNoPhotosFoundState = provideNoPhotosFoundStateUseCase.execute()
    val observableRefreshState = provideRefreshStateUseCase.execute()

    fun filterByCamera(camera: String) {
        filterByCameraNameUseCase.execute(camera)
    }

    fun clearFilter() {
        clearFilterUseCase.execute()
    }

    fun loadFromApiAndSetIntoDatabase(sol: String, rover: String, isRefreshing: Boolean) {
        loadFromApiAndSetIntoDatabaseUseCase.execute(sol, rover, isRefreshing)
    }

    fun requestNewSol() {
        requestNewSolUseCase.execute()
    }

    fun refreshData() {
        refreshDataUseCase.execute()
    }

}