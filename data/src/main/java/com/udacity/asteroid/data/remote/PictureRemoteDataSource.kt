package com.udacity.asteroid.data.remote

import com.udacity.asteroid.data.mapper.ErrorMapper
import com.udacity.asteroid.data.mapper.PictureMapper
import com.udacity.asteroid.data.network.ApiManagerMoshi
import com.udacity.asteroid.data.response.PictureResponse
import com.udacity.asteroid.data.source.picture.PictureDataSource
import com.udacity.asteroid.data.storage.database.AsteroidDao
import com.udacity.asteroid.data.util.RetrofitErrorUtil
import com.udacity.asteroid.domain.model.ErrorModel
import com.udacity.asteroid.domain.model.PictureModel
import com.udacity.asteroid.domain.util.ResultType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PictureRemoteDataSource(private val asteroidDao: AsteroidDao) :
    PictureDataSource {

    override suspend fun get(): ResultType<PictureModel, ErrorModel> {

        return try {
            val response = ApiManagerMoshi.get().pictureOfTheDay()
            if (response.isSuccessful) {
                val pictureOfTheDayResponse = response.body()!!
                savePicture(pictureOfTheDayResponse)
                ResultType.Success(
                    PictureMapper.transformResponseToModel(
                        pictureOfTheDayResponse
                    )
                )
            } else {
                val error = RetrofitErrorUtil.parseError(response)!!
                ResultType.Error(ErrorMapper.transformResponseToModel(error))
            }

        } catch (t: Throwable) {
            ResultType.Error(ErrorUtils.errorHandler(t))
        }
    }

    private suspend fun savePicture(pictureResponse: PictureResponse) =
        withContext(Dispatchers.IO) {
            asteroidDao.deletePicture()
            asteroidDao.insertPicture(
                PictureMapper.transformResponseToEntity(pictureResponse)
            )
        }

}