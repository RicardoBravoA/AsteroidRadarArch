package com.udacity.asteroid.data.storage.source

import com.udacity.asteroid.data.mapper.AsteroidMapper
import com.udacity.asteroid.data.source.asteroid.AsteroidOfflineDataSource
import com.udacity.asteroid.data.storage.database.AsteroidDao
import com.udacity.asteroid.domain.model.AsteroidModel
import com.udacity.asteroid.domain.model.ErrorModel
import com.udacity.asteroid.domain.util.ResultType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AsteroidLocalDataSource(private val asteroidDao: AsteroidDao) : AsteroidOfflineDataSource {

    override suspend fun list(
        startDate: String,
        endDate: String
    ): ResultType<List<AsteroidModel>, ErrorModel> = withContext(Dispatchers.IO) {

        try {
            val response = asteroidDao.getAsteroidList(startDate, endDate)
            return@withContext ResultType.Success(AsteroidMapper.transformEntityToModel(response))
        } catch (t: Throwable) {
            return@withContext ResultType.Error(ErrorModel())
        }
    }

    override suspend fun delete(currentDate: String) = withContext(Dispatchers.IO) {
        asteroidDao.deleteOldAsteroids(currentDate)

    }

}