package com.udacity.asteroid.data.source

interface AsteroidOfflineDataStore : AsteroidDataStore {

    suspend fun delete(currentDate: String)

}