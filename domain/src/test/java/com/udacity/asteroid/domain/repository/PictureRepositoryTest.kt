package com.udacity.asteroid.domain.repository

import com.udacity.asteroid.domain.model.ErrorModel
import com.udacity.asteroid.domain.model.PictureModel
import com.udacity.asteroid.domain.util.MainCoroutineRule
import com.udacity.asteroid.domain.util.ResultType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PictureRepositoryTest {

    private var pictureRepository = FakePictureRepository()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val pictureModel = PictureModel(
        "url", "mediaType", "title",
        "date", "explanation"
    )

    private val error = ErrorModel("Test exception")

    @Test
    fun get_Success() = mainCoroutineRule.runBlockingTest {
        pictureRepository.savePicture(pictureModel)
        pictureRepository.setReturnError(false)

        val data = pictureRepository.get() as ResultType.Success
        assertThat(data.value, IsEqual(pictureModel))
    }

    @Test
    fun get_Error() = mainCoroutineRule.runBlockingTest {
        pictureRepository.setReturnError(true)

        val data = pictureRepository.get() as ResultType.Error
        assertThat(data.value, IsEqual(error))
    }

    @Test
    fun save_Success() = mainCoroutineRule.runBlockingTest {
        pictureRepository.savePicture(pictureModel)
        Assert.assertNotNull((pictureRepository.data))
        assertThat(pictureRepository.data, Is.`is`(pictureModel))
    }

    @Test
    fun save_Error() = mainCoroutineRule.runBlockingTest {
        Assert.assertNull((pictureRepository.data))
    }

}