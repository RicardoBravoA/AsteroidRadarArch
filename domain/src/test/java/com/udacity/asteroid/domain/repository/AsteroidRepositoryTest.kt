package com.udacity.asteroid.domain.repository

import com.udacity.asteroid.domain.model.AsteroidModel
import com.udacity.asteroid.domain.model.ErrorModel
import com.udacity.asteroid.domain.util.MainCoroutineRule
import com.udacity.asteroid.domain.util.ResultType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsEqual
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AsteroidRepositoryTest {

    private var asteroidRepository = FakeAsteroidRepository()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val asteroidModel1 = AsteroidModel(
        0L, "Codename 0", "closeApproachDate 0",
        0.0, 0.0, 0.0, 0.0, true
    )

    private val asteroidModel2 = AsteroidModel(
        1L, "Codename 1", "closeApproachDate 1",
        0.0, 0.0, 0.0, 0.0, true
    )

    private val asteroidModel3 = AsteroidModel(
        2L, "Codename 2", "closeApproachDate 2",
        0.0, 0.0, 0.0, 0.0, false
    )

    private val error = ErrorModel("Test exception")
    private var list = linkedMapOf<Long, AsteroidModel>()
    private lateinit var result: List<AsteroidModel>

    @Before
    fun setup() {
        list[asteroidModel1.id] = asteroidModel1
        list[asteroidModel2.id] = asteroidModel2
        list[asteroidModel3.id] = asteroidModel3

        result = listOf(asteroidModel1, asteroidModel2, asteroidModel3)
    }

    @Test
    fun list_Success() = mainCoroutineRule.runBlockingTest {
        asteroidRepository.saveAsteroid(result)
        asteroidRepository.setReturnError(false)

        val data = asteroidRepository.list("startDate", "endDate") as ResultType.Success
        assertThat(data.value, IsEqual(result))
    }

    @Test
    fun list_Error() = mainCoroutineRule.runBlockingTest {
        asteroidRepository.setReturnError(true)

        val data = asteroidRepository.list("startDate", "endDate") as ResultType.Error
        assertThat(data.value, IsEqual(error))
    }

    @Test
    fun save_Success() = mainCoroutineRule.runBlockingTest {
        asteroidRepository.saveAsteroid(result)
        assertFalse((asteroidRepository.data.isEmpty()))
        assertThat(asteroidRepository.data, `is`(list))
        assertThat(asteroidRepository.data.size, `is`(list.size))
    }

    @Test
    fun save_Error() = mainCoroutineRule.runBlockingTest {
        assertTrue((asteroidRepository.data.isEmpty()))
        assertThat(asteroidRepository.data.size, `is`(0))
    }

    @Test
    fun validateData() = mainCoroutineRule.runBlockingTest {

        val delta = 1e-15

        val list = listOf(asteroidModel1)
        asteroidRepository.saveAsteroid(list)
        asteroidRepository.setReturnError(false)

        val data = asteroidRepository.list("startDate", "endDate") as ResultType.Success
        assertNotNull(data.value)
        assertThat(data.value, IsEqual(list))

        val model = data.value.first()

        assertEquals(model.id, asteroidModel1.id)
        assertEquals(model.codename, asteroidModel1.codename)
        assertEquals(model.closeApproachDate, asteroidModel1.closeApproachDate)
        assertEquals(model.absoluteMagnitude, asteroidModel1.absoluteMagnitude, delta)
        assertEquals(model.estimatedDiameter, asteroidModel1.estimatedDiameter, delta)
        assertEquals(model.relativeVelocity, asteroidModel1.relativeVelocity, delta)
        assertEquals(model.distanceFromEarth, asteroidModel1.distanceFromEarth, delta)
        assertEquals(model.isPotentiallyHazardous, asteroidModel1.isPotentiallyHazardous)

    }

}