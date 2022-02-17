package com.ravnnerdery.data.useCases

import com.ravnnerdery.domain.models.MarsPhoto
import com.ravnnerdery.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ProvidePhotoListUseCase {
    fun execute(): Flow<List<MarsPhoto>>
}

class ProvidePhotoListUseCaseImpl @Inject constructor(private val repo: MainRepository) : ProvidePhotoListUseCase {
    override fun execute(): Flow<List<MarsPhoto>> {
        return repo.providePhotosList()
    }
}