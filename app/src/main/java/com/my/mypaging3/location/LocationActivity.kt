package com.my.mypaging3.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.OnTokenCanceledListener
import com.my.mypaging3.R


/*
Optimize location for battery
1. Frequently
2. Accuracy
3. Delivery time
 */

class LocationActivity : AppCompatActivity() {

    private lateinit var geofencingClient: GeofencingClient

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions: MutableMap<String, Boolean> ->

        val message = if (permissions.containsKey(FINE_LOCATION) &&
            permissions.containsKey(COARSE_LOCATION)
        ) {
            "GRANTED"
        } else {
            "DENY"
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)


        Log.d(
            "TAG",
            "FusedLocationProviderClient(this).lastLocation = ${FusedLocationProviderClient(this).lastLocation}"
        )
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        Log.d(
            "TAG", "FusedLocationProviderClient(this).lastLocation = ${
                FusedLocationProviderClient(this).getCurrentLocation(102, object :
                    CancellationToken() {
                    override fun isCancellationRequested(): Boolean {
                        return false
                    }

                    override fun onCanceledRequested(p0: OnTokenCanceledListener): CancellationToken {
                        return this
                    }
                })
            }"
        )
        // Check if need explain for user
        if (ContextCompat.checkSelfPermission(
                this,
                FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        // && ContextCompat.checkSelfPermission(
        //     this,
        //     COARSE_LOCATION
        // ) == PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(this, "ALREADY GRANTED", Toast.LENGTH_LONG).show()
        } else {
            locationPermissionRequest.launch(arrayOf(FINE_LOCATION, COARSE_LOCATION))
        }

        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        //locationManager.requestLocationUpdates()
    }

    private companion object {
        const val FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
        const val COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION
    }
}