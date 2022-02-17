package com.ravnnerdery.nasa_challenge.viewModels

import androidx.lifecycle.ViewModel
import com.ravnnerdery.data.useCases.ProvideEnlargedPhotoUseCase
import com.ravnnerdery.domain.models.MarsPhoto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val provideEnlargedPhotoUseCase: ProvideEnlargedPhotoUseCase
) : ViewModel() {
    fun enlargedPhoto(id: Long): Flow<MarsPhoto> {
        return provideEnlargedPhotoUseCase.execute(id)
    }
}
