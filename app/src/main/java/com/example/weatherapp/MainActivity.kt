package com.example.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.model.MyDataItem
import com.example.weatherapp.service.ApiInterface
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class MainActivity : AppCompatActivity() {


    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: com.google.android.gms.location.LocationRequest

    private var PERMISSION_ID = 1000
    var fullLocation :String?=null

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        getLastLocation()
        locationList()

        binding.apicallBtn.setOnClickListener {
            val changePage = Intent(this, ListActivity::class.java)
            changePage.putExtra("fulllocation", fullLocation)
            startActivity(changePage)
        }

    }

    //Function to list locations
    fun locationList(){
        Handler(Looper.getMainLooper()).postDelayed(
            {
                val apiInterface = ApiInterface.create().getData(fullLocation.toString())
                apiInterface.enqueue( object : Callback<List<MyDataItem>>{
                    override fun onResponse(call: Call<List<MyDataItem>>?, response: Response<List<MyDataItem>>?) {

                        var locationList : List<MyDataItem> = response?.body() as List<MyDataItem>
                        var locationName = arrayOfNulls<String>(locationList!!.size)

                        for (i in locationList!!.indices){
                            locationName[i] = locationList!![i]!!.latt_long
                        }
                        var adapter = ArrayAdapter<String>(applicationContext,android.R.layout.simple_dropdown_item_1line,locationName)
                        binding.locationListView.adapter=adapter

                    }
                    override fun onFailure(call: Call<List<MyDataItem>>?, t: Throwable?) {

                    }
                })
            },
            1000 // value in milliseconds
        )
    }




    // Function that allows us to get our last position
    @SuppressLint("MissingPermission", "SetTextI18n")
    private fun getLastLocation(){
        //Check permission
        if (CheckPermission()){
            // Check the location service is enabled
            if (isLocationEnabled()){
                //Lets get the location
                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                    var location = task.result
                    if (location == null){
                        getNewLocation()
                    }else{

                        binding.textLocation.text="Lat:" +location.latitude +"; Long:"+location.longitude
                        binding.textCity.text=getCityName(location.latitude,location.longitude)
                        fullLocation=""+location.latitude+","+location.longitude
                    }

                }
            }else{
                Toast.makeText(this,"Please Enabled your location service",Toast.LENGTH_LONG).show()
            }
        }else{
            RequestPermission()
        }

    }


    @SuppressLint("MissingPermission")
    private fun getNewLocation(){
        locationRequest = com.google.android.gms.location.LocationRequest()
        locationRequest.priority=com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval=0
        locationRequest.fastestInterval=0
        locationRequest.numUpdates=2
        fusedLocationProviderClient!!.requestLocationUpdates(
            locationRequest,locationCallback, Looper.myLooper()
        )
    }

    private val locationCallback = object : LocationCallback(){
        override fun onLocationResult(p0: LocationResult?) {
            var lastLocation = p0?.lastLocation
            binding.textLocation.text="" + lastLocation!!.latitude +"; Long:"+lastLocation.longitude
            binding.textCity.text=getCityName(lastLocation.latitude,lastLocation.longitude)
            fullLocation=""+lastLocation.latitude+","+lastLocation.longitude
        }
    }


    //Need to create a function that will check the uses permissions
    private fun CheckPermission() : Boolean{
        if(
            ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ){
            return true
        }
        return false
    }

    //Create a function that will allow us to get user permission
    private fun RequestPermission(){
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION),PERMISSION_ID
        )

    }

    //A function that check if the location service of the device is enabled
    private fun isLocationEnabled() : Boolean{

        var locationManager : LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)  || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

    }


    //function to get the city name
    private fun getCityName(lat : Double , long : Double) : String{
        System.out.println("long:"+long+"lat:"+lat)
        var CityName =""
        var geocoder = Geocoder(this, Locale.getDefault())
        var Adress : MutableList<Address>? = geocoder.getFromLocation(lat,long,1)

        System.out.println(Adress?.get(0))
        if (Adress != null) {
            CityName = Adress.get(0).adminArea
        }
        return CityName
    }


    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        //this is a buil in function that check the permission result
        if(requestCode == PERMISSION_ID){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.d("Debug","you have the permission")
            }
        }
    }





}