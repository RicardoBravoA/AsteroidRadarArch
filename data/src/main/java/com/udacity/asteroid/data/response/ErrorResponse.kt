package com.udacity.asteroid.data.response

import com.udacity.asteroid.domain.util.ConstantError

data class ErrorResponse(
    var message: String? = ConstantError.ERROR
)