package com.udacity.asteroid.radar.main

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroid.data.util.DataDateUtil
import com.udacity.asteroid.domain.model.AsteroidModel
import com.udacity.asteroid.domain.model.PictureModel
import com.udacity.asteroid.domain.repository.AsteroidRepository
import com.udacity.asteroid.domain.repository.PictureRepository
import com.udacity.asteroid.domain.util.ResultType
import com.udacity.asteroid.radar.mapper.MainMapper
import com.udacity.asteroid.radar.util.NetworkStatus
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainViewModel(
    private val asteroidRepository: AsteroidRepository,
    private val pictureRepository: PictureRepository
) : ViewModel() {

    private val _status = MutableLiveData<NetworkStatus>()
    val status: LiveData<NetworkStatus>
        get() = _status

    private val _asteroidList = MutableLiveData<List<MainItem>>()
    val asteroidList: LiveData<List<MainItem>>
        get() = _asteroidList

    init {
        getData(
            DataDateUtil.currentDate(),
            DataDateUtil.currentDate(DataDateUtil.DEFAULT_END_DATE_DAYS)
        )
    }

    fun weekData() {
        getData(
            DataDateUtil.currentDate(),
            DataDateUtil.currentDate(DataDateUtil.DEFAULT_END_DATE_DAYS)
        )
    }

    fun today() {
        getData(
            DataDateUtil.currentDate(),
            DataDateUtil.currentDate()
        )
    }

    fun saved() {
        getData(
            DataDateUtil.currentDate(),
            DataDateUtil.currentDate(DataDateUtil.DEFAULT_END_DATE_DAYS)
        )
    }

    @VisibleForTesting
    private fun getData(startDate: String, endDate: String) {
        viewModelScope.launch {
            _status.value = NetworkStatus.LOADING

            try {
                coroutineScope {

                    var items = listOf<AsteroidModel>()
                    var picture: PictureModel? = null

                    when (val result = pictureRepository.get()) {
                        is ResultType.Success -> {
                            picture = result.value
                        }
                        is ResultType.Error -> {
                            //Do nothing
                        }
                    }

                    when (val result = asteroidRepository.list(startDate, endDate)) {
                        is ResultType.Success -> {
                            items = result.value
                        }
                        is ResultType.Error -> {
                            //Do nothing
                        }
                    }

                    _asteroidList.postValue(MainMapper.transform(items, picture))
                    _status.value = NetworkStatus.DONE
                }
            } catch (e: Exception) {
                _asteroidList.value = arrayListOf()
                _status.value = NetworkStatus.ERROR
            }
        }
    }

}