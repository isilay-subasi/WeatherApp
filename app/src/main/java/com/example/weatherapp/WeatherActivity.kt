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


        var date_list =intent.getStringArrayListExtra("date_list")
        var date_list_length= date_list!!.size
        System.out.println("weatheract:"+date_list_length)

        //location
        var city_name = intent.getStringExtra("city_name")
        binding.textCity.text=city_name

        //min degree
        var min_degree = intent.getStringExtra("min_degree")
        min_degree= min_degree?.substringBefore(".")
        min_degree=min_degree+"°C"
        binding.textMin.text="Min Temp:"+min_degree

        //max degree
        var max_degree = intent.getStringExtra("max_degree")
        max_degree= max_degree?.substringBefore(".")
        max_degree=max_degree+"°C"
        binding.textMax.text="Max Temp:"+max_degree


        //current degree
        var current_degree = intent.getStringExtra("current_degree")
        current_degree= current_degree?.substringBefore(".")
        current_degree=current_degree+"°C"
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

        var firstDate = date_list?.get(1)
        binding.firstDateText.text=firstDate
        var todayWeatherFirst = state_name_arrayList?.get(1)
        if(todayWeatherFirst == "Light Cloud"){
            binding.firstDateImage.setImageResource(R.drawable.lightcloud)

        }else if (todayWeatherFirst == "Heavy Cloud"){
            binding.firstDateImage.setImageResource(R.drawable.heavycloud)
        }else if (todayWeatherFirst == "Snow"){
            binding.firstDateImage.setImageResource(R.drawable.snow)
        }else if (todayWeatherFirst == "Sleet"){
            binding.firstDateImage.setImageResource(R.drawable.sleet)
        }else if (todayWeatherFirst == "Hail"){
            binding.firstDateImage.setImageResource(R.drawable.hail)
        }else if (todayWeatherFirst == "Thunderstorm"){
            binding.firstDateImage.setImageResource(R.drawable.thunderstorm)
        }else if (todayWeatherFirst == "Heavy Rain"){
            binding.firstDateImage.setImageResource(R.drawable.heavyrain)
        }else if (todayWeatherFirst == "Light Rain"){
            binding.firstDateImage.setImageResource(R.drawable.lightrain2)
        }else if (todayWeatherFirst == "Showers"){
            binding.firstDateImage.setImageResource(R.drawable.showers)
        }else if (todayWeatherFirst == "Clear"){
            binding.firstDateImage.setImageResource(R.drawable.clear)
        }else{
            binding.firstDateImage.setImageResource(R.drawable.snow)
        }




        var secondDate = date_list?.get(2)
        binding.secondDateText.text=secondDate
        var todayWeatherSecond = state_name_arrayList?.get(2)
        if(todayWeatherSecond == "Light Cloud"){
            binding.secondDateImage.setImageResource(R.drawable.lightcloud)

        }else if (todayWeatherSecond == "Heavy Cloud"){
            binding.secondDateImage.setImageResource(R.drawable.heavycloud)
        }else if (todayWeatherSecond == "Snow"){
            binding.secondDateImage.setImageResource(R.drawable.snow)
        }else if (todayWeatherSecond == "Sleet"){
            binding.secondDateImage.setImageResource(R.drawable.sleet)
        }else if (todayWeatherSecond == "Hail"){
            binding.secondDateImage.setImageResource(R.drawable.hail)
        }else if (todayWeatherSecond == "Thunderstorm"){
            binding.secondDateImage.setImageResource(R.drawable.thunderstorm)
        }else if (todayWeatherSecond == "Heavy Rain"){
            binding.secondDateImage.setImageResource(R.drawable.heavyrain)
        }else if (todayWeatherSecond == "Light Rain"){
            binding.secondDateImage.setImageResource(R.drawable.lightrain2)
        }else if (todayWeatherSecond == "Showers"){
            binding.secondDateImage.setImageResource(R.drawable.showers)
        }else if (todayWeatherSecond == "Clear"){
            binding.secondDateImage.setImageResource(R.drawable.clear)
        }else{
            binding.secondDateImage.setImageResource(R.drawable.snow)
        }


        var thirdDate = date_list?.get(3)
        var endDate = date_list?.get(4)
        var endOnDate = date_list?.get(5)
        binding.thirdDateText.text=thirdDate
        binding.endDateText.text=endDate
        binding.endOneDateText.text=endOnDate

        var todayWeatherThird = state_name_arrayList?.get(3)
        if(todayWeatherThird == "Light Cloud"){
            binding.thirdDateImage.setImageResource(R.drawable.lightcloud)

        }else if (todayWeatherThird == "Heavy Cloud"){
            binding.thirdDateImage.setImageResource(R.drawable.heavycloud)
        }else if (todayWeatherThird == "Snow"){
            binding.thirdDateImage.setImageResource(R.drawable.snow)
        }else if (todayWeatherThird == "Sleet"){
            binding.thirdDateImage.setImageResource(R.drawable.sleet)
        }else if (todayWeatherThird == "Hail"){
            binding.thirdDateImage.setImageResource(R.drawable.hail)
        }else if (todayWeatherThird == "Thunderstorm"){
            binding.thirdDateImage.setImageResource(R.drawable.thunderstorm)
        }else if (todayWeatherThird == "Heavy Rain"){
            binding.thirdDateImage.setImageResource(R.drawable.heavyrain)
        }else if (todayWeatherThird == "Light Rain"){
            binding.thirdDateImage.setImageResource(R.drawable.lightrain2)
        }else if (todayWeatherThird == "Showers"){
            binding.thirdDateImage.setImageResource(R.drawable.showers)
        }else if (todayWeatherThird == "Clear"){
            binding.thirdDateImage.setImageResource(R.drawable.clear)
        }else{
            binding.thirdDateImage.setImageResource(R.drawable.snow)
        }

        var todayWeatherEnd = state_name_arrayList?.get(4)
        if(todayWeatherEnd == "Light Cloud"){
            binding.endDateImage.setImageResource(R.drawable.lightcloud)

        }else if (todayWeatherEnd == "Heavy Cloud"){
            binding.endDateImage.setImageResource(R.drawable.heavycloud)
        }else if (todayWeatherEnd == "Snow"){
            binding.endDateImage.setImageResource(R.drawable.snow)
        }else if (todayWeatherEnd == "Sleet"){
            binding.endDateImage.setImageResource(R.drawable.sleet)
        }else if (todayWeatherEnd == "Hail"){
            binding.endDateImage.setImageResource(R.drawable.hail)
        }else if (todayWeatherEnd == "Thunderstorm"){
            binding.endDateImage.setImageResource(R.drawable.thunderstorm)
        }else if (todayWeatherEnd == "Heavy Rain"){
            binding.endDateImage.setImageResource(R.drawable.heavyrain)
        }else if (todayWeatherEnd == "Light Rain"){
            binding.endDateImage.setImageResource(R.drawable.lightrain2)
        }else if (todayWeatherEnd == "Showers"){
            binding.endDateImage.setImageResource(R.drawable.showers)
        }else if (todayWeatherEnd == "Clear"){
            binding.endDateImage.setImageResource(R.drawable.clear)
        }else{
            binding.endDateImage.setImageResource(R.drawable.snow)
        }

        var todayWeatherEndOne = state_name_arrayList?.get(5)
        if(todayWeatherEndOne == "Light Cloud"){
            binding.endOneImage.setImageResource(R.drawable.lightcloud)

        }else if (todayWeatherEndOne == "Heavy Cloud"){
            binding.endOneImage.setImageResource(R.drawable.heavycloud)
        }else if (todayWeatherEndOne == "Snow"){
            binding.endOneImage.setImageResource(R.drawable.snow)
        }else if (todayWeatherEndOne == "Sleet"){
            binding.endOneImage.setImageResource(R.drawable.sleet)
        }else if (todayWeatherEndOne == "Hail"){
            binding.endOneImage.setImageResource(R.drawable.hail)
        }else if (todayWeatherEndOne == "Thunderstorm"){
            binding.endOneImage.setImageResource(R.drawable.thunderstorm)
        }else if (todayWeatherEndOne == "Heavy Rain"){
            binding.endOneImage.setImageResource(R.drawable.heavyrain)
        }else if (todayWeatherEndOne == "Light Rain"){
            binding.endOneImage.setImageResource(R.drawable.lightrain2)
        }else if (todayWeatherEndOne == "Showers"){
            binding.endOneImage.setImageResource(R.drawable.showers)
        }else if (todayWeatherEndOne == "Clear"){
            binding.endOneImage.setImageResource(R.drawable.clear)
        }else{
            binding.endOneImage.setImageResource(R.drawable.snow)
        }


    }

}