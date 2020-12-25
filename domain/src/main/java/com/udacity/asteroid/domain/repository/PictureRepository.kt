package com.udacity.asteroid.domain.repository

import com.udacity.asteroid.domain.model.ErrorModel
import com.udacity.asteroid.domain.model.PictureModel
import com.udacity.asteroid.domain.util.ResultType

interface PictureRepository {

    suspend fun get(): ResultType<PictureModel, ErrorModel>

    suspend fun savePicture(pictureModel: PictureModel)

}