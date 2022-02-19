package com.ravnnerdery.data.useCases

import com.ravnnerdery.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ProvideFilterNamesUseCase {
    fun execute(): Flow<List<String>>
}

class ProvideFilterNamesUseCaseImpl @Inject constructor(private val repo: MainRepository) :
    ProvideFilterNamesUseCase {
    override fun execute(): Flow<List<String>> {
        return repo.provideFilterNames()
    }
}