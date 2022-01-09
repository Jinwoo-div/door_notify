package com.choi.door_notify.data.remote

import android.util.Log
import com.choi.door_notify.data.entities.ForecastRequest
import com.choi.door_notify.ui.WeatherView
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherService {


    private lateinit var transData: WeatherView

    fun setTransData(view: WeatherView) {
        this.transData = view

    }
    fun makeRetrofit(): Retrofit {
        val retrofit = Retrofit.Builder().baseUrl("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/").addConverterFactory(
            GsonConverterFactory.create()).build()

        return retrofit
    }

    fun OnedayWeather(req: ForecastRequest) {
        val retro = makeRetrofit()
        val weatherService = retro.create(WeatherRetrofitInterface::class.java)


        val call = weatherService.getOnedayWeather(
            req.numOfRows,
            req.pageNo,
            req.dataType,
            req.baseDate,
            req.baseTime,
            req.nx,
            req.ny
        )

        call.enqueue(object : retrofit2.Callback<WeatherData> {
            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                if (response.isSuccessful) {
                    Log.d("item", response.body()!!.response?.body?.dataType.toString())

                }
            }

            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}