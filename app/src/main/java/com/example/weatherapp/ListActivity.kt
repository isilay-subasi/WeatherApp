package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.databinding.ActivityListBinding
import com.example.weatherapp.model.WeatherData
import com.example.weatherapp.service.ApiWeatherInterface
import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

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
                        woeid[position]=cityList!![position]!!.woeid.toString()
                        System.out.println(woeid[position].toString()+"/")// woeid

                        run("https://www.metaweather.com/api/location/2487956/")

/*

                        val apiWeatherInterface = ApiWeatherInterface.create().getData(woeid[position].toString())
                        apiWeatherInterface.enqueue(object : Callback<List<WeatherData>>{
                            override fun onResponse(
                                call: Call<List<WeatherData>>,
                                response: Response<List<WeatherData>>
                            ) {

                                System.out.println("deneeee"+response)
                            }

                            override fun onFailure(
                                call: Call<List<WeatherData>>,
                                t: Throwable
                            ) {

                            }

                        })
 */

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

    fun run(url: String) {
        val request = okhttp3.Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback{
            override fun onFailure(call: okhttp3.Call, e: IOException) {

            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {

                val responseStr = response.body().toString()
                System.out.println("ayyyyyyyyyy"+responseStr)

            }

        })




    }




}




