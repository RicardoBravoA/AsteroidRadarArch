package com.udacity.asteroid.radar.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udacity.asteroid.domain.model.AsteroidModel
import com.udacity.asteroid.radar.mapper.DetailMapper

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private val _detailItemList = MutableLiveData<List<DetailModel>>()
    val detailItemList: LiveData<List<DetailModel>>
        get() = _detailItemList

    fun transformData(asteroidModel: AsteroidModel) {
        _detailItemList.value =
            DetailMapper.transform(getApplication<Application>().baseContext, asteroidModel)

    }

}