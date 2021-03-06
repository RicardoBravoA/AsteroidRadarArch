package com.udacity.asteroid.data.source.asteroid

import android.content.Context
import com.udacity.asteroid.data.storage.database.AsteroidDatabase
import com.udacity.asteroid.data.util.isInternet
import com.udacity.asteroid.domain.repository.AsteroidRepository

class AsteroidDataSourceFactory(private val context: Context) {

    fun create(): AsteroidRepository {
        val asteroidDatabase = AsteroidDatabase.getDatabase(context)
        val value = if (context.isInternet()) Preference.CLOUD else Preference.DB

        return if (Preference.CLOUD == value) {
            AsteroidRemoteDataSource(asteroidDatabase.asteroidDao())
        } else {
            AsteroidLocalDataSource(asteroidDatabase.asteroidDao())
        }
    }

    enum class Preference {
        CLOUD, DB
    }

}