package com.udacity.asteroid.radar.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.asteroid.data.source.asteroid.AsteroidDataSourceFactory
import com.udacity.asteroid.data.source.picture.PictureDataSourceFactory

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {

            val asteroidRepository = AsteroidDataSourceFactory(app).create()
            val pictureRepository = PictureDataSourceFactory(app).create()

            return MainViewModel(
                asteroidRepository,
                pictureRepository
            ) as T
        }
        throw IllegalArgumentException("Unable to construct view model")
    }
}