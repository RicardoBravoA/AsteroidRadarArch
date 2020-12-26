package com.udacity.asteroid.data.storage.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.udacity.asteroid.data.storage.entity.AsteroidEntity
import com.udacity.asteroid.data.storage.entity.PictureEntity
import com.udacity.asteroid.data.util.DataDateUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.*
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class AsteroidDaoTest {

    private lateinit var database: AsteroidDatabase
    private lateinit var asteroidEntity: AsteroidEntity
    private lateinit var pictureEntity: PictureEntity

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AsteroidDatabase::class.java
        ).build()

        asteroidEntity =
            AsteroidEntity(
                0L, "Codename 0", DataDateUtil.currentDate(),
                0.0, 0.0, 0.0, 0.0, true
            )

        pictureEntity = PictureEntity(
            "url", "mediaType", "title",
            "date", "explanation"
        )
    }

    @After
    fun closeDatabase() = database.close()

    @Test
    fun insertAsteroidAndGetById() = runBlockingTest {
        database.asteroidDao().insertAsteroid(asteroidEntity)

        val response = database.asteroidDao().getAsteroidById(asteroidEntity.id)

        assertThat(response as AsteroidEntity, notNullValue())
        assertThat(response.id, `is`(asteroidEntity.id))
        assertThat(response.codename, `is`(asteroidEntity.codename))
        assertThat(response.closeApproachDate, `is`(asteroidEntity.closeApproachDate))
        assertThat(response.absoluteMagnitude, `is`(asteroidEntity.absoluteMagnitude))
        assertThat(response.estimatedDiameter, `is`(asteroidEntity.estimatedDiameter))
        assertThat(response.relativeVelocity, `is`(asteroidEntity.relativeVelocity))
        assertThat(response.distanceFromEarth, `is`(asteroidEntity.distanceFromEarth))
        assertThat(response.isPotentiallyHazardous, `is`(asteroidEntity.isPotentiallyHazardous))
    }

    @Test
    fun validateNoData() = runBlockingTest {
        val response = database.asteroidDao().getAsteroidList(
            DataDateUtil.currentDate(),
            DataDateUtil.currentDate(DataDateUtil.DEFAULT_END_DATE_DAYS)
        )
        assertThat(response.size, `is`(0))
    }

    @Test
    fun validateInsertAsteroid() = runBlockingTest {
        database.asteroidDao().insertAsteroid(asteroidEntity)

        val response = database.asteroidDao().getAsteroidList(
            DataDateUtil.currentDate(),
            DataDateUtil.currentDate(DataDateUtil.DEFAULT_END_DATE_DAYS)
        )

        assertThat(response.size, `is`(1))
    }

    @Test
    fun validateAndNotGetAsteroidsById() = runBlockingTest {
        database.asteroidDao().insertAsteroid(asteroidEntity)

        val response = database.asteroidDao().getAsteroidById(999L)
        Assert.assertNull(response)
    }

    @Test
    fun validateNotPictureData() = runBlockingTest {
        val response = database.asteroidDao().getPicture()
        Assert.assertNull(response)
    }

    @Test
    fun validateInsertPicture() = runBlockingTest {
        database.asteroidDao().insertPicture(asteroidEntity)

        val response = database.asteroidDao().getAsteroidList(
            DataDateUtil.currentDate(),
            DataDateUtil.currentDate(DataDateUtil.DEFAULT_END_DATE_DAYS)
        )

        assertThat(response.size, `is`(1))
    }

}