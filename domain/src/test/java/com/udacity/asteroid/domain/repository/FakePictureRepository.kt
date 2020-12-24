package com.udacity.asteroid.domain.repository

import com.udacity.asteroid.domain.model.ErrorModel
import com.udacity.asteroid.domain.model.PictureModel
import com.udacity.asteroid.domain.util.ResultType

class FakePictureRepository : PictureRepository {

    private var data: PictureModel? = null

    private var shouldReturnError = false

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override suspend fun get(): ResultType<PictureModel, ErrorModel> {
        if (shouldReturnError) {
            return ResultType.Error(ErrorModel("Test exception"))
        }
        return ResultType.Success(data!!)
    }

    override suspend fun savePicture(pictureModel: PictureModel) {
        data = pictureModel
    }

}