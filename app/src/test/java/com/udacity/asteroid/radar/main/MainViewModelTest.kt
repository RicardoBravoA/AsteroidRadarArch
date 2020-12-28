package com.udacity.asteroid.radar.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.udacity.asteroid.domain.repository.FakeAsteroidRepository
import com.udacity.asteroid.domain.repository.FakePictureRepository
import com.udacity.asteroid.radar.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

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

}