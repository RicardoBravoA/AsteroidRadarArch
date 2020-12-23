package com.udacity.asteroid.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailItem(
    val title: String, val subTitle: String, val showIcon: Boolean = false
) : Parcelable