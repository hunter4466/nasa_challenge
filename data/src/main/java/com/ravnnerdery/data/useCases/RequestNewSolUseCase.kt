package com.ravnnerdery.data.useCases

import com.ravnnerdery.domain.repository.MainRepository
import javax.inject.Inject

interface RequestNewSolUseCase {
    fun execute()
}

class RequestNewSolUseCaseImpl @Inject constructor(private val repo: MainRepository) :
    RequestNewSolUseCase {
    override fun execute() {
        repo.requestNewSol()
    }
}