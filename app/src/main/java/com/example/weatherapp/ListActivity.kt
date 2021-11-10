package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.weatherapp.databinding.ActivityListBinding
import okhttp3.OkHttpClient
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class ListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityListBinding
    private val client = OkHttpClient()

    private var requestQueue: RequestQueue? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        requestQueue = Volley.newRequestQueue(this)
        val fullLocation = intent.getStringExtra("fulllocation")
        val apiInterface = ApiInterface.create().getData(fullLocation.toString())
        apiInterface.enqueue( object : Callback<List<MyDataItem>> {
            override fun onResponse(call: Call<List<MyDataItem>>?, response: Response<List<MyDataItem>>?) {

                System.out.println(response?.body())
                var cityList : List<MyDataItem>? = response?.body() as List<MyDataItem>
                var woeidList : List<MyDataItem>? = response?.body() as List<MyDataItem>

                var city = arrayOfNulls<String>(cityList!!.size)
                var woeid = arrayOfNulls<String>(woeidList!!.size)


                for (i in cityList!!.indices){
                    city[i] = cityList!![i]!!.title
                    woeid[i]=cityList!![i]!!.woeid.toString()

                }

                //woeid list
                for(i in woeidList!!.indices){
                    woeid[i] = woeidList!![i]!!.woeid.toString()
                    System.out.println("woeid:"+woeid[i])
                }


                var adapter = ArrayAdapter<String>(applicationContext,android.R.layout.simple_dropdown_item_1line,city)
                binding.listView.adapter=adapter
                binding.listView.onItemClickListener = object : AdapterView.OnItemClickListener {

                    override fun onItemClick(
                        parent: AdapterView<*>, view: View,
                        position: Int, id: Long
                    ) {
                        // value of item that is clicked
                        val itemValue = binding.listView.getItemAtPosition(position) as String
                        city[position] = cityList!![position]!!.title
                        woeid[position]=cityList!![position]!!.woeid.toString()
                        System.out.println(woeid[position].toString()+"/")// woeid

                        var urlString : String = woeid[position].toString()+"/"
                        run("https://www.metaweather.com/api/location/"+urlString,city[position])



                    }
                }


                if(response?.body() != null){

                    System.out.println("isil:"+response.body())


                }

            }

            override fun onFailure(call: Call<List<MyDataItem>>?, t: Throwable?) {

            }
        })


    }

    fun run(url: String, cityName: String?) {
        val request = okhttp3.Request.Builder()
            .url(url)
            .build()


        client.newCall(request).enqueue(object : okhttp3.Callback{
            override fun onFailure(call: okhttp3.Call, e: IOException) {

            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val jsonData: String = response.body()!!.string()
                val Jobject = JSONObject(jsonData)
                System.out.println("ayyyyyyyyyy"+Jobject)

                val jsonObject = JSONTokener(Jobject.toString()).nextValue() as JSONObject
                val consolidated_weather = jsonObject.getJSONArray("consolidated_weather")
                val state_name_arrayList = ArrayList<String>()
                var date_list=ArrayList<String>()

                //current degree
                val current_degree = consolidated_weather.getJSONObject(0).getString("the_temp")
                //min degree
                val min_degree = consolidated_weather.getJSONObject(0).getString("min_temp")
                //max degree
                val max_degree = consolidated_weather.getJSONObject(0).getString("max_temp")


                for (i in 0 until consolidated_weather.length()){
                    //weather_state_name
                    val weather_state_name = consolidated_weather.getJSONObject(i).getString("weather_state_name")
                    state_name_arrayList.add(weather_state_name)
                }
                for (i in 0 until consolidated_weather.length()){
                    //weather_state_name
                    val date_list_name= consolidated_weather.getJSONObject(i).getString("applicable_date")
                    date_list.add(date_list_name)
                }


                val thread = Thread {
                    val changeWeather = Intent(this@ListActivity,WeatherActivity::class.java)
                    changeWeather.putExtra("state_name_arrayList",state_name_arrayList)
                    changeWeather.putExtra("date_list",date_list)
                    changeWeather.putExtra("current_degree",current_degree)
                    changeWeather.putExtra("min_degree",min_degree)
                    changeWeather.putExtra("max_degree",max_degree)
                    changeWeather.putExtra("city_name",cityName)
                    startActivity(changeWeather)

                }
                thread.start()

            }

        })




    }


    fun runWeekApi(woeidValue : String ) {
        val sdf = SimpleDateFormat("yyyy/M/dd")
        val currentDate = sdf.format(Date())
        var url = "https://www.metaweather.com/api/location/" + woeidValue + "/" + currentDate
        System.out.println("url"+url)
        val request = okhttp3.Request.Builder()
            .url(url)
            .build()


        client.newCall(request).enqueue(object : okhttp3.Callback{
            override fun onFailure(call: okhttp3.Call, e: IOException) {

            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val jsonData: String = response.body()!!.string()
                val JArray = JSONArray(jsonData)
                System.out.println("tarihlijson"+JArray)
            }


        })

    }


}






