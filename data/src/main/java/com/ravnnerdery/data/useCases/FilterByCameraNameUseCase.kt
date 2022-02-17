package com.ravnnerdery.data.useCases

import com.ravnnerdery.domain.repository.MainRepository
import javax.inject.Inject

interface FilterByCameraNameUseCase {
    fun execute(camera: String)
}

class FilterByCameraNameUseCaseImpl @Inject constructor(private val repo: MainRepository): FilterByCameraNameUseCase {
    override fun execute(camera: String) {
        repo.filterByCameraName(camera)
    }
}