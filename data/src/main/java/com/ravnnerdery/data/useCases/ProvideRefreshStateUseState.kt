package com.ravnnerdery.data.useCases

import com.ravnnerdery.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ProvideRefreshStateUseCase {
    fun execute(): Flow<Boolean>
}

class ProvideRefreshStateUseCaseImpl @Inject constructor(private val repo: MainRepository) :
    ProvideRefreshStateUseCase {
    override fun execute(): Flow<Boolean> {
        return repo.provideRefreshState()
    }
}