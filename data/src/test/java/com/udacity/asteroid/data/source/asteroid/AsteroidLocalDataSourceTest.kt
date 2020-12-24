package com.udacity.asteroid.data.source.asteroid

import com.udacity.asteroid.domain.model.AsteroidModel
import com.udacity.asteroid.domain.model.ErrorModel
import com.udacity.asteroid.domain.repository.AsteroidRepository
import com.udacity.asteroid.domain.util.ResultType

class AsteroidLocalDataSourceTest(private var data: LinkedHashMap<Long, AsteroidModel>) :

    AsteroidRepository {
    override suspend fun list(
        startDate: String,
        endDate: String
    ): ResultType<List<AsteroidModel>, ErrorModel> {
        return ResultType.Success(data.values.toList())
    }

    override suspend fun saveAsteroid(list: List<AsteroidModel>) {
        list.forEach {
            data[it.id] = it
        }

    }

}