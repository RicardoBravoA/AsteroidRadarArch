package com.udacity.asteroid.radar.util.bindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.udacity.asteroid.data.util.isInternet
import com.udacity.asteroid.radar.R

@BindingAdapter("imageUrl")
fun ImageView.bindImage(url: String?) {

    url?.let {
        Picasso.get()
            .load(url)
            .error(R.drawable.ic_no_photo)
            .into(this)
        scaleType = if (context.isInternet()) {
            ImageView.ScaleType.CENTER_CROP
        } else {
            ImageView.ScaleType.CENTER_INSIDE
        }

    }
}

@BindingAdapter("statusIcon")
fun ImageView.bindAsteroidStatusImage(isHazardous: Boolean) {
    if (isHazardous) {
        setImageResource(R.drawable.ic_status_potentially_hazardous)
        contentDescription = context.getString(R.string.potentially_hazardous_asteroid_image)
    } else {
        setImageResource(R.drawable.ic_status_normal)
        contentDescription = context.getString(R.string.not_hazardous_asteroid_image)
    }
}

@BindingAdapter("accessibilityPictureOfTheDay")
fun ImageView.bindAccessibilityPictureOfTheDay(value: String) {
    contentDescription =
        context.getString(R.string.nasa_picture_of_day_content_description_format, value)
}

@BindingAdapter("asteroidStatusImage")
fun ImageView.bindDetailsStatusImage(isHazardous: Boolean) {
    if (isHazardous) {
        setImageResource(R.drawable.asteroid_hazardous)
        contentDescription = context.getString(R.string.potentially_hazardous_asteroid_image)
    } else {
        setImageResource(R.drawable.asteroid_safe)
        contentDescription = context.getString(R.string.not_hazardous_asteroid_image)
    }
}