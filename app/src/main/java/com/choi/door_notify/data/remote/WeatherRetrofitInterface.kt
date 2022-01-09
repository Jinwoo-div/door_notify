package com.choi.door_notify.data.remote

import com.choi.door_notify.data.entities.ForecastRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherRetrofitInterface {
    @GET("getVilageFcst?serviceKey=7Hho5ZMQKmUZFqp9bIgIwVQInlIDGio%2FeTeo%2BMzidgSZ9CIHqXXvGFlX2%2FosFuPXsprSe%2BeRWwDgglppd5aVLw%3D%3D")
    fun getOnedayWeather(
        @Query("numOfRows") numOfRows: Int,
        @Query("pageNo") pageNo: Int,
        @Query("dataType") dataType: String,
        @Query("base_date") baseDate: String,
        @Query("base_time") baseTime: String,
        @Query("nx") nx: Int,
        @Query("ny") ny: Int
    ): Call<WeatherData>

}