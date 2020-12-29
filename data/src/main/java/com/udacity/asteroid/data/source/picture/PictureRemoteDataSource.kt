package com.udacity.asteroid.data.source.picture

import com.udacity.asteroid.data.mapper.ErrorMapper
import com.udacity.asteroid.data.mapper.PictureMapper
import com.udacity.asteroid.data.network.ApiManagerMoshi
import com.udacity.asteroid.domain.repository.PictureRepository
import com.udacity.asteroid.data.storage.database.AsteroidDao
import com.udacity.asteroid.data.util.ErrorUtil
import com.udacity.asteroid.data.util.RetrofitErrorUtil
import com.udacity.asteroid.data.util.wrapEspressoIdlingResource
import com.udacity.asteroid.domain.model.ErrorModel
import com.udacity.asteroid.domain.model.PictureModel
import com.udacity.asteroid.domain.util.ResultType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PictureRemoteDataSource(private val asteroidDao: AsteroidDao) :
    PictureRepository {

    override suspend fun get(): ResultType<PictureModel, ErrorModel> {
        wrapEspressoIdlingResource {
            return withContext(Dispatchers.IO) {
                try {
                    val response = ApiManagerMoshi.get().pictureOfTheDay()
                    if (response.isSuccessful) {
                        val pictureOfTheDayResponse = response.body()!!
                        savePicture(
                            PictureMapper.transformResponseToModel(
                                pictureOfTheDayResponse
                            )
                        )
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
                    ResultType.Error(ErrorUtil.errorHandler(t))
                }
            }
        }


    }

    override suspend fun savePicture(pictureModel: PictureModel) {
        wrapEspressoIdlingResource {
            return withContext(Dispatchers.IO) {
                asteroidDao.deletePicture()
                asteroidDao.insertPicture(
                    PictureMapper.transformModelToEntity(pictureModel)
                )
            }
        }
    }
}