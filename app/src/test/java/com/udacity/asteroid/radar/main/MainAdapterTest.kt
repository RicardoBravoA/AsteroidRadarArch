package com.udacity.asteroid.radar.main
/*
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.udacity.asteroid.domain.model.AsteroidModel
import com.udacity.asteroid.domain.model.PictureModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainAdapterTest {

    private lateinit var mainAdapter: MainAdapter
    private var list = mutableListOf<MainItem>()
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
    fun setUp() {
        list.add(MainItem.Picture(pictureModel.url, pictureModel.mediaType, pictureModel.title))
        list.add(MainItem.Item(asteroidModel1))
        list.add(MainItem.Item(asteroidModel2))
        list.add(MainItem.Item(asteroidModel3))
    }

    @Test
    fun getItemTest() {
        mainAdapter = MainAdapter(::asteroidClick)

        mainAdapter.notifyDataSetChanged()
        mainAdapter.submitList(list)

        val item = mainAdapter.item(0) as MainItem.Picture
        Assert.assertEquals(pictureModel.url, item.url)
    }

    private fun asteroidClick(asteroidModel: AsteroidModel) {
        println(asteroidModel.toString())
    }

}*/
