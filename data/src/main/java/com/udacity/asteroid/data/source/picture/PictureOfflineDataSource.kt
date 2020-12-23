package com.udacity.asteroid.data.source.picture

interface PictureOfflineDataSource : PictureDataSource {

    suspend fun delete()

}