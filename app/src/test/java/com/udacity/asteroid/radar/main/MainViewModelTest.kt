package com.udacity.asteroid.radar.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.udacity.asteroid.domain.model.AsteroidModel
import com.udacity.asteroid.domain.model.PictureModel
import com.udacity.asteroid.domain.repository.FakeAsteroidRepository
import com.udacity.asteroid.domain.repository.FakePictureRepository
import com.udacity.asteroid.radar.util.MainCoroutineRule
import com.udacity.asteroid.radar.util.NetworkStatus
import com.udacity.asteroid.radar.util.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var asteroidRepository: FakeAsteroidRepository
    private lateinit var pictureRepository: FakePictureRepository

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
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

    private val pictureModel = PictureModel(
        "url", "mediaType", "title",
        "date", "explanation"
    )


    @Before
    fun setupViewModel() = runBlockingTest {
        asteroidRepository = FakeAsteroidRepository()
        pictureRepository = FakePictureRepository()

        asteroidRepository.saveAsteroid(listOf(asteroidModel1, asteroidModel2, asteroidModel3))
        pictureRepository.savePicture(pictureModel)

        mainViewModel = MainViewModel(asteroidRepository, pictureRepository)
    }

    @Test
    fun getData_weekTest() {
        mainCoroutineRule.pauseDispatcher()

        mainViewModel.weekData()

        val value = mainViewModel.asteroidList.getOrAwaitValue()

        mainCoroutineRule.resumeDispatcher()

        assertThat(value, not(nullValue()))

        val status = mainViewModel.status.getOrAwaitValue()
        assertThat(status, `is`(NetworkStatus.DONE))
    }

    @Test
    fun getData_todayTest() {
        mainCoroutineRule.pauseDispatcher()

        mainViewModel.today()

        val value = mainViewModel.asteroidList.getOrAwaitValue()

        mainCoroutineRule.resumeDispatcher()

        assertThat(value, not(nullValue()))

        val status = mainViewModel.status.getOrAwaitValue()
        assertThat(status, `is`(NetworkStatus.DONE))
    }

    @Test
    fun getData_savedTest() {
        mainCoroutineRule.pauseDispatcher()

        mainViewModel.saved()

        val value = mainViewModel.asteroidList.getOrAwaitValue()

        mainCoroutineRule.resumeDispatcher()

        assertThat(value, not(nullValue()))

        val status = mainViewModel.status.getOrAwaitValue()
        assertThat(status, `is`(NetworkStatus.DONE))
    }


}