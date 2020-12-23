package com.udacity.asteroid.data.mapper

import com.udacity.asteroid.data.response.ErrorResponse
import com.udacity.asteroid.domain.model.ErrorModel

object ErrorMapper {

    fun transformResponseToModel(errorResponse: ErrorResponse): ErrorModel {
        return ErrorModel(
            errorResponse.message
        )
    }

}