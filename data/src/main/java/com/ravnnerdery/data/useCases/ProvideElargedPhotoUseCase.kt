package com.ravnnerdery.data.useCases

import com.ravnnerdery.domain.models.MarsPhoto
import com.ravnnerdery.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ProvideEnlargedPhotoUseCase {
    fun execute(id: Long): Flow<MarsPhoto>
}

class ProvideEnlargedPhotoUseCaseImpl @Inject constructor(private val repo: MainRepository) : ProvideEnlargedPhotoUseCase {
    override fun execute(id: Long): Flow<MarsPhoto> {
        return repo.provideEnlargedPhoto(id)
    }
}