package com.udacity.asteroid.radar.mapper

import com.udacity.asteroid.domain.model.AsteroidModel
import com.udacity.asteroid.domain.model.PictureModel

object MainMapper {

    fun transform(items: List<AsteroidModel>, picture: PictureModel?): List<MainItem> {
        val list = mutableListOf<MainItem>()
        list.add(transformPicture(picture))

        items.forEach {
            list.add(MainItem.Item(it))
        }
        return list
    }

    private fun transformPicture(picture: PictureModel?): MainItem.Picture {
        var mainPicture = MainItem.Picture("", "")
        picture?.let {
            mainPicture = MainItem.Picture(it.url, it.mediaType, it.title)
        }
        return mainPicture
    }


}