package com.udacity.asteroid.data.source.asteroid

interface AsteroidOfflineDataSource : AsteroidDataSource {

    suspend fun delete(currentDate: String)

}