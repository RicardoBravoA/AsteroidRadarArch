package com.udacity.asteroid.radar.util

import android.view.View

fun View.show(value: Boolean = true) {
    visibility = if (value) {
        View.VISIBLE
    } else {
        View.GONE
    }
}