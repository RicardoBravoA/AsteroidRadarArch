package com.udacity.asteroid.data.remote

import com.udacity.asteroid.data.mapper.AsteroidMapper
import com.udacity.asteroid.data.mapper.ErrorMapper
import com.udacity.asteroid.data.network.ApiManager
import com.udacity.asteroid.data.response.AsteroidResponse
import com.udacity.asteroid.domain.repository.AsteroidRepository
import com.udacity.asteroid.data.storage.database.AsteroidDao
import com.udacity.asteroid.data.util.NetworkUtils
import com.udacity.asteroid.data.util.RetrofitErrorUtil
import com.udacity.asteroid.domain.model.AsteroidModel
import com.udacity.asteroid.domain.model.ErrorModel
import com.udacity.asteroid.domain.util.ResultType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AsteroidRemoteRepository(private val asteroidDao: AsteroidDao) :
    AsteroidRepository {

    override suspend fun list(
        startDate: String,
        endDate: String
    ): ResultType<List<AsteroidModel>, ErrorModel> {

        val response = ApiManager.get().feed(startDate, endDate)
        return if (response.isSuccessful) {
            val asteroidString = response.body()
            val asteroidList = NetworkUtils.parseStringToAsteroidList(asteroidString!!)
            saveAsteroid(asteroidList)
            ResultType.Success(AsteroidMapper.transformResponseToModel(asteroidList))
        } else {
            val error = RetrofitErrorUtil.parseError(response)!!
            ResultType.Error(ErrorMapper.transformResponseToModel(error))
        }
    }

    private suspend fun saveAsteroid(list: List<AsteroidResponse>) = withContext(Dispatchers.IO) {
        list.forEach {
            asteroidDao.insertAsteroid(
                AsteroidMapper.transformAsteroidResponseToEntity(
                    it
                )
            )
        }
    }

}