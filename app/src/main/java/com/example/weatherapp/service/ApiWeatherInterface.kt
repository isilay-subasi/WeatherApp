package com.example.weatherapp.service

import com.example.weatherapp.model.WeatherData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiWeatherInterface {


    @GET("/")
    fun getData(searchString: String): Call<List<WeatherData>>

    companion object {

        var BASE_URL = "https://www.metaweather.com/api/location"

        fun create(): ApiWeatherInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiWeatherInterface::class.java)

        }

    }


}