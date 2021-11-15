package com.example.weatherapp.service

import com.example.weatherapp.model.MyDataItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("api/location/search/?")
    fun getData(@Query("lattlong") searchString: String): Call<List<MyDataItem>>

    companion object {

        var BASE_URL = "https://www.metaweather.com/"

        fun create(): ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }

    }
}