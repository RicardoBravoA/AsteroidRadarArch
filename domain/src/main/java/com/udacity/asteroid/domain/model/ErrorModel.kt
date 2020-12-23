package com.udacity.asteroid.domain.model

import com.udacity.asteroid.domain.util.ConstantError

data class ErrorModel(
    var message: String? = ConstantError.ERROR
)