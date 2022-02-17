package com.ravnnerdery.nasa_challenge.viewModels

import androidx.lifecycle.ViewModel
import com.ravnnerdery.data.useCases.FilterByCameraNameUseCase
import com.ravnnerdery.data.useCases.ProvideFilterNamesUseCase
import com.ravnnerdery.data.useCases.ProvidePhotoListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    providePhotoListUseCase: ProvidePhotoListUseCase,
    provideFilterNamesUseCase: ProvideFilterNamesUseCase,
    private val filterByCameraNameUseCase: FilterByCameraNameUseCase
) : ViewModel() {
    val observablePhotoList = providePhotoListUseCase.execute()
    val observableFilterList = provideFilterNamesUseCase.execute()

    fun filterByCamera(camera: String){
       filterByCameraNameUseCase.execute(camera)
    }
}