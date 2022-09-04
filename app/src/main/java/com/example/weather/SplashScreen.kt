package com.example.weather

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.jar.Manifest




class SplashScreen() : AppCompatActivity(), Parcelable {
    lateinit var mfusedlocation:FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        mfusedlocation = LocationServices.getFusedLocationProviderClient(this)

        getLastLocation()
    }
       @SuppressLint("MissingPermission")
       private fun getLastLocation() {
           if(CheckPermission()){
               if(LocationEnable()){
                   mfusedlocation.LastLocation.addOnCompleteListener
                   {
                       task->
                       var location:Location?=task.result

                   }
               }
           }
               else{
                   RequestPermission()

               }
           }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("Not yet implemented")
    }


}

    private fun LocationEnable(): Boolean {

    }

    private fun RequestPermission(): Any {

    }

    private fun CheckPermission(): Boolean {

    }







