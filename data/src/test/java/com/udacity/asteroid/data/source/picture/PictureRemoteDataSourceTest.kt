package com.udacity.asteroid.data.source.picture

import com.udacity.asteroid.data.util.MainCoroutineRule
import com.udacity.asteroid.domain.model.ErrorModel
import com.udacity.asteroid.domain.model.PictureModel
import com.udacity.asteroid.domain.util.ResultType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PictureRemoteDataSourceTest {

    private var pictureRepository = FakePictureRemoteDataSourceTest()

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
        MatcherAssert.assertThat(data.value, IsEqual(pictureModel))
    }

    @Test
    fun get_Error() = mainCoroutineRule.runBlockingTest {
        pictureRepository.setReturnError(true)

        val data = pictureRepository.get() as ResultType.Error
        Assert.assertNotNull(data.value)
        MatcherAssert.assertThat(data.value, IsEqual(error))
    }

    @Test
    fun save_Success() = mainCoroutineRule.runBlockingTest {
        pictureRepository.savePicture(pictureModel)
        Assert.assertNotNull((pictureRepository.data))
        MatcherAssert.assertThat(pictureRepository.data, Is.`is`(pictureModel))
    }

    @Test
    fun save_Error() = mainCoroutineRule.runBlockingTest {
        Assert.assertNull((pictureRepository.data))
    }

    @Test
    fun validateData_Success() = mainCoroutineRule.runBlockingTest {
        pictureRepository.savePicture(pictureModel)
        pictureRepository.setReturnError(false)

        val data = pictureRepository.get() as ResultType.Success
        val model = data.value

        Assert.assertNotNull(model)
        MatcherAssert.assertThat(model, Is.`is`(pictureModel))

        Assert.assertEquals(model.url, pictureModel.url)
        Assert.assertEquals(model.mediaType, pictureModel.mediaType)
        Assert.assertEquals(model.title, pictureModel.title)
        Assert.assertEquals(model.date, pictureModel.date)
        Assert.assertEquals(model.explanation, pictureModel.explanation)
    }

    @Test
    fun validateData_Error() = mainCoroutineRule.runBlockingTest {
        pictureRepository.setReturnError(true)

        val data = pictureRepository.get() as ResultType.Error
        Assert.assertNotNull(data.value)
        MatcherAssert.assertThat(data.value, IsEqual(error))

        val model = data.value

        Assert.assertEquals(model.message, error.message)
    }

}