package com.udacity.asteroid.data.storage.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroid.data.storage.entity.AsteroidEntity
import com.udacity.asteroid.data.storage.entity.PictureEntity

@Dao
interface AsteroidDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAsteroid(asteroid: AsteroidEntity)

    @Query("select * from asteroid where id = :id")
    fun getAsteroidById(id: Long): AsteroidEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPicture(picture: PictureEntity)

    @Query("select * from asteroid where date(closeApproachDate) between :startDate and :endDate order by date(closeApproachDate)")
    fun getAsteroidList(startDate: String, endDate: String): List<AsteroidEntity>

    @Query("select * from picture")
    fun getPicture(): PictureEntity

    @Query("delete from picture")
    fun deletePicture()

    @Query("delete from asteroid where date(closeApproachDate) < :currentDate")
    fun deleteOldAsteroids(currentDate: String)

}