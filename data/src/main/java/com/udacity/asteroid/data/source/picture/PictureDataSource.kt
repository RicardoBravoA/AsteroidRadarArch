package com.udacity.asteroid.data.source.picture

import com.udacity.asteroid.domain.model.ErrorModel
import com.udacity.asteroid.domain.model.PictureModel
import com.udacity.asteroid.domain.util.ResultType

interface PictureDataSource {

    suspend fun get(): ResultType<PictureModel, ErrorModel>
}