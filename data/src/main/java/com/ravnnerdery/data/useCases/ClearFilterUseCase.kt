package com.ravnnerdery.data.useCases

import com.ravnnerdery.domain.repository.MainRepository
import javax.inject.Inject

interface ClearFilterUseCase {
    fun execute()
}

class ClearFilterUseCaseImpl @Inject constructor(private val repo: MainRepository) :
    ClearFilterUseCase {
    override fun execute() {
        repo.clearFilter()
    }
}