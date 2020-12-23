package com.udacity.asteroid.data.network

import com.udacity.asteroid.data.response.PictureResponse
import com.udacity.asteroid.data.util.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("neo/rest/v1/feed")
    suspend fun feed(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String = Constant.KEY
    ): Response<String>

    @GET("planetary/apod")
    suspend fun pictureOfTheDay(
        @Query("api_key") apiKey: String = Constant.KEY
    ): Response<PictureResponse>

}