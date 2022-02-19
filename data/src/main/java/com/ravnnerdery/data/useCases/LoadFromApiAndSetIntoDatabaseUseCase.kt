package com.ravnnerdery.data.useCases

import com.ravnnerdery.domain.repository.MainRepository
import javax.inject.Inject

interface LoadFromApiAndSetIntoDatabaseUseCase {
    fun execute(sol: String, rover: String, isRefreshing: Boolean)
}

class LoadFromApiAndSetIntoDatabaseUseCaseImpl @Inject constructor(private val repo: MainRepository) :
    LoadFromApiAndSetIntoDatabaseUseCase {
    override fun execute(sol: String, rover: String, isRefreshing: Boolean) {
        repo.loadFromApiAndSetIntoDatabase(sol, rover, isRefreshing)
    }
}