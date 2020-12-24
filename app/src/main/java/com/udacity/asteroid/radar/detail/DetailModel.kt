package com.udacity.asteroid.radar.detail

import com.udacity.asteroid.domain.model.DetailItemModel

sealed class DetailModel {
    data class Item(val detailItem: DetailItemModel) : DetailModel() {
        override val id = Long.MIN_VALUE
    }

    data class Picture(val isPotentiallyHazardous: Boolean) :
        DetailModel() {
        override val id = Long.MIN_VALUE
    }

    abstract val id: Long
}