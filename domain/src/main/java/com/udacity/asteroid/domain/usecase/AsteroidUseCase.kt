package com.udacity.asteroid.domain.usecase

import com.udacity.asteroid.domain.model.AsteroidModel
import com.udacity.asteroid.domain.model.ErrorModel
import com.udacity.asteroid.domain.repository.AsteroidRepository
import com.udacity.asteroid.domain.util.ResultType

class AsteroidUseCase(private val asteroidRepository: AsteroidRepository) {

    suspend fun list(
        startDate: String,
        endDate: String
    ): ResultType<List<AsteroidModel>, ErrorModel> {
        return asteroidRepository.list(startDate, endDate)
    }
}