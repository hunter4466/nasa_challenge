package com.ravnnerdery.data.useCases

import com.ravnnerdery.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ProvideNoPhotosFoundStateUseCase {
    fun execute(): Flow<String>
}

class ProvideNoPhotosFoundStateUseCaseImpl @Inject constructor(val repo: MainRepository) :
    ProvideNoPhotosFoundStateUseCase {
    override fun execute(): Flow<String> {
        return repo.provideNoPhotosFoundState()
    }
}