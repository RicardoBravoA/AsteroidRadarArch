package com.udacity.asteroid.radar.main

import com.udacity.asteroid.domain.repository.FakeAsteroidRepository
import com.udacity.asteroid.domain.repository.FakePictureRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var asteroidRepository: FakeAsteroidRepository
    private lateinit var pictureRepository: FakePictureRepository

}