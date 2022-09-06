@file:Suppress("DEPRECATION")

package com.example.weather

import android.app.DownloadManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var city:TextView
    lateinit var coordinates:TextView
    lateinit var status:TextView
    lateinit var temp:TextView
    lateinit var temp_min:TextView
    lateinit var temp_max:TextView
    lateinit var clouds:TextView
    lateinit var feelsLike:TextView
    lateinit var wind:TextView
    lateinit var humidity:TextView
    lateinit var pressure:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val lat=intent.getStringExtra("lat")
        val long=intent.getStringExtra("long")
        window.statusBarColor=Color.parseColor("#122259")
        getJsonData(lat,long)
    }
    private fun getJsonData(lat:String?,long:String?){
        val API_KEY = "3a26a1f25c0f300c7640d29e28aac0da"
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.openweathermap.org/data/2.5/weather?lat=$lat&lon=$long&appid=$API_KEY&units=metric"
        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, url,null,
            Response.Listener { response ->
                setValues(response)
                 },
            Response.ErrorListener {Toast.makeText(this, "Error",Toast.LENGTH_LONG).show()}
        )

// Add the request to the RequestQueue.
        queue.add(jsonRequest)
    }
    private fun setValues(response:JSONObject)
    { city = findViewById(R.id.city)
        coordinates = findViewById(R.id.coordinates)
        status = findViewById(R.id.status)
        temp = findViewById(R.id.temp)
        temp_min = findViewById(R.id.temp_min)
        temp_max = findViewById(R.id.temp_max)
        clouds = findViewById(R.id.clouds)
        feelsLike = findViewById(R.id.feelsLike)
        wind = findViewById(R.id.wind)
        humidity = findViewById(R.id.humidity)
        pressure = findViewById(R.id.pressure)

       // displaying cityname
        city.text = response.getString("name")

       // displaying co-ordinat
        var lat = response.getJSONObject("coord").getString("lat")
        var long = response.getJSONObject("coord").getString("lon")
        coordinates.text = "$lat, $long"

        // displaying weather status
        status.text = response.getJSONArray("weather").getJSONObject(0).getString("main")

        // displaying temperature
        temp.text = response.getJSONObject("main").getString("temp")+ "°C"
        // displaying max and min temperature
        temp_max.text = "Max Temp: " +response.getJSONObject("main").getString("temp_max")+ "°C"
        temp_min.text = "Min Temp: " +response.getJSONObject("main").getString("temp_min")+ "°C"

        //displaying wind, pressure & humidity
        wind.text = response.getJSONObject("wind").getString("speed")
        humidity.text = response.getJSONObject("main").getString("humidity")+"%"
        pressure.text = response.getJSONObject("main").getString("pressure")

        //displaying feels like temperature & cloudiness %
        feelsLike.text = response.getJSONObject("main").getString("feels_like")+"°C"
        clouds.text = response.getJSONObject("clouds").getString("all")+"%"







    }

}