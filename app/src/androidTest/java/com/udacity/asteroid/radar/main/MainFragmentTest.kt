package com.udacity.asteroid.radar.main

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.udacity.asteroid.domain.model.AsteroidModel
import com.udacity.asteroid.domain.model.PictureModel
import com.udacity.asteroid.domain.repository.AsteroidRepository
import com.udacity.asteroid.domain.repository.PictureRepository
import com.udacity.asteroid.domain.repository.FakeAsteroidRepository
import com.udacity.asteroid.domain.repository.FakePictureRepository
import com.udacity.asteroid.radar.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
class MainFragmentTest {

    private lateinit var asteroidRepository: AsteroidRepository
    private lateinit var pictureRepository: PictureRepository
    private lateinit var navController: NavController

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
    fun initRepository() {
        asteroidRepository = FakeAsteroidRepository()
        pictureRepository = FakePictureRepository()

        val scenario = launchFragmentInContainer<MainFragment>(
            Bundle(),
            R.style.Theme_AsteroidRadar
        )
        navController = Mockito.mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }
    }

    @Test
    fun validateDataInRecyclerView() {
        runBlocking {
            asteroidRepository.saveAsteroid(listOf(asteroidModel1, asteroidModel2, asteroidModel3))
            pictureRepository.savePicture(pictureModel)
        }

        Espresso.onView(ViewMatchers.withId(R.id.asteroid_recycler_view)).check(
            ViewAssertions.matches(
                ViewMatchers.hasChildCount(4)
            )
        )
    }

}