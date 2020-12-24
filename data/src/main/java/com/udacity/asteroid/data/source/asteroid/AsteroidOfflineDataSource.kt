package com.udacity.asteroid.data.source.asteroid

import com.udacity.asteroid.domain.repository.AsteroidRepository

interface AsteroidOfflineDataSource : AsteroidRepository {

    suspend fun delete(currentDate: String)

}