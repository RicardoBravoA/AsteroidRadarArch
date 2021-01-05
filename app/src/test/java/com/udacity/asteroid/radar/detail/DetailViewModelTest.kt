package com.udacity.asteroid.radar.detail

import android.app.Application
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.LargeTest
import androidx.test.filters.MediumTest
import com.udacity.asteroid.domain.model.AsteroidModel
import com.udacity.asteroid.radar.util.MainCoroutineRule
import com.udacity.asteroid.radar.util.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/*@RunWith(RobolectricTestRunner::class)
@LargeTest*/
@RunWith(RobolectricTestRunner::class)
@ExperimentalCoroutinesApi
@Config(sdk = [Build.VERSION_CODES.P])
class DetailViewModelTest {

    private lateinit var detailViewModel: DetailViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private val application = ApplicationProvider.getApplicationContext<Application>()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val asteroidModel = AsteroidModel(
        0L, "Codename 0", "closeApproachDate 0",
        0.0, 0.0, 0.0, 0.0, true
    )

    @Before
    fun init() {
        detailViewModel = DetailViewModel(application)
    }

    @Test
    fun transformDataTest() = mainCoroutineRule.runBlockingTest {
        mainCoroutineRule.pauseDispatcher()

        detailViewModel.transformData(asteroidModel)
        val value = detailViewModel.detailItemList.getOrAwaitValue()

        mainCoroutineRule.resumeDispatcher()
        assertThat(value, not(nullValue()))
    }

}