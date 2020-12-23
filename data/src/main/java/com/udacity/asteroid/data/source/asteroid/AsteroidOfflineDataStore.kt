package com.udacity.asteroid.data.source.asteroid

interface AsteroidOfflineDataStore : AsteroidDataStore {

    suspend fun delete(currentDate: String)

}