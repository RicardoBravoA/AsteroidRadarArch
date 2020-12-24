package com.udacity.asteroid.data.source.picture

import com.udacity.asteroid.domain.repository.PictureRepository

interface PictureOfflineDataSource : PictureRepository {

    suspend fun delete()

}