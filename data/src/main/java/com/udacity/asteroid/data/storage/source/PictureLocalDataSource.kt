package com.udacity.asteroid.data.storage.source

import com.udacity.asteroid.data.mapper.PictureMapper
import com.udacity.asteroid.data.source.picture.PictureOfflineDataSource
import com.udacity.asteroid.data.storage.database.AsteroidDao
import com.udacity.asteroid.domain.model.ErrorModel
import com.udacity.asteroid.domain.model.PictureModel
import com.udacity.asteroid.domain.util.ResultType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PictureLocalDataSource(private val asteroidDao: AsteroidDao) : PictureOfflineDataSource {

    override suspend fun get(): ResultType<PictureModel, ErrorModel> = withContext(Dispatchers.IO) {

        try {
            val response = asteroidDao.getPicture()
            return@withContext ResultType.Success(PictureMapper.transformEntityToModel(response))
        } catch (t: Throwable) {
            return@withContext ResultType.Error(ErrorModel())
        }
    }

    override suspend fun delete() {
        asteroidDao.deletePicture()
    }
}