package com.udacity.asteroid.domain.repository

import com.udacity.asteroid.domain.model.AsteroidModel
import com.udacity.asteroid.domain.model.ErrorModel
import com.udacity.asteroid.domain.util.ResultType

class FakeAsteroidRepository : AsteroidRepository {

    var data: LinkedHashMap<String, AsteroidModel> = LinkedHashMap()

    private var shouldReturnError = false

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override suspend fun list(
        startDate: String,
        endDate: String
    ): ResultType<List<AsteroidModel>, ErrorModel> {
        if (shouldReturnError) {
            return ResultType.Error(ErrorModel("Test exception"))
        }
        return ResultType.Success(data.values.toList())
    }

}