package com.udacity.asteroid.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PictureModel(
    val url: String,
    val mediaType: String,
    val title: String,
    val date: String,
    val explanation: String
) : Parcelable