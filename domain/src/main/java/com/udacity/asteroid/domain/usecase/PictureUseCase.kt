package com.udacity.asteroid.domain.usecase

import com.udacity.asteroid.domain.model.ErrorModel
import com.udacity.asteroid.domain.model.PictureModel
import com.udacity.asteroid.domain.repository.PictureRepository
import com.udacity.asteroid.domain.util.ResultType

class PictureUseCase(private val pictureRepository: PictureRepository) {

    suspend fun get(): ResultType<PictureModel, ErrorModel> {
        return pictureRepository.get()
    }

}