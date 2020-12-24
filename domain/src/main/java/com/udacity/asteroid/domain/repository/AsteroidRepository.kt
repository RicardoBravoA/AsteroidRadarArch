package com.udacity.asteroid.domain.repository

import com.udacity.asteroid.domain.model.AsteroidModel
import com.udacity.asteroid.domain.model.ErrorModel
import com.udacity.asteroid.domain.util.ResultType

interface AsteroidRepository {

    suspend fun list(
        startDate: String,
        endDate: String
    ): ResultType<List<AsteroidModel>, ErrorModel>
}