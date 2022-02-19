package com.ravnnerdery.data.useCases

import com.ravnnerdery.domain.repository.MainRepository
import javax.inject.Inject

interface RefreshDataUseCase {
    fun execute()
}

class RefreshDataUseCaseImpl @Inject constructor(private val repo: MainRepository) :
    RefreshDataUseCase {
    override fun execute() {
        repo.refreshData()
    }
}