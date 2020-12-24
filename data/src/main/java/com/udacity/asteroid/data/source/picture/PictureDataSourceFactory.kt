package com.udacity.asteroid.data.source.picture

import android.content.Context
import com.udacity.asteroid.data.storage.database.AsteroidDatabase
import com.udacity.asteroid.data.util.isInternet
import com.udacity.asteroid.domain.repository.PictureRepository

class PictureDataSourceFactory(private val context: Context) {

    fun create(): PictureRepository {
        val asteroidDatabase = AsteroidDatabase.getDatabase(context)
        val value = if (context.isInternet()) Preference.CLOUD else Preference.DB

        return if (Preference.CLOUD == value) {
            PictureRemoteRepository(asteroidDatabase.asteroidDao)
        } else {
            PictureLocalDataSource(asteroidDatabase.asteroidDao)
        }
    }

    enum class Preference {
        CLOUD, DB
    }

}