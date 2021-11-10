package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.databinding.ActivityWeatherBinding

class WeatherActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWeatherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val state_name_arrayList = intent.getStringArrayListExtra("state_name_arrayList")
        System.out.println("weatheract:"+state_name_arrayList)

        //current degree
        val current_degree = intent.getStringExtra("current_degree")
        binding.currentValue.text=current_degree


        var todayWeather = state_name_arrayList?.get(0)
        if(todayWeather == "Light Cloud"){
            binding.mainImage.setImageResource(R.drawable.lightcloud)

        }else if (todayWeather == "Heavy Cloud"){
            binding.mainImage.setImageResource(R.drawable.heavycloud)
        }else if (todayWeather == "Snow"){
            binding.mainImage.setImageResource(R.drawable.snow)
        }else if (todayWeather == "Sleet"){
            binding.mainImage.setImageResource(R.drawable.sleet)
        }else if (todayWeather == "Hail"){
            binding.mainImage.setImageResource(R.drawable.hail)
        }else if (todayWeather == "Thunderstorm"){
            binding.mainImage.setImageResource(R.drawable.thunderstorm)
        }else if (todayWeather == "Heavy Rain"){
            binding.mainImage.setImageResource(R.drawable.heavyrain)
        }else if (todayWeather == "Light Rain"){
            binding.mainImage.setImageResource(R.drawable.lightrain2)
        }else if (todayWeather == "Showers"){
            binding.mainImage.setImageResource(R.drawable.showers)
        }else if (todayWeather == "Clear"){
            binding.mainImage.setImageResource(R.drawable.clear)
        }else{
            binding.mainImage.setImageResource(R.drawable.snow)
        }

    }
}