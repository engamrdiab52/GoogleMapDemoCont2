package com.amrabdelhamiddiab.googlemapdemocont2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.amrabdelhamiddiab.googlemapdemocont2.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnPolylineClickListener {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private val alexandria = LatLng(31.20588470349843, 29.91779871421426)
    private val cairo = LatLng(30.04640852760722, 31.236832732735163)
    private val mansoura = LatLng(31.04343698358825, 31.378033567808494)
    private val siwa = LatLng(29.280285360336215, 25.5088921201458)
    private val cyprus = LatLng(34.760781889258894, 32.626692560378494)
    private val lebanon = LatLng(34.052888370057616, 35.84241239159556)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in Sydney and move the camera
        val alexandria = LatLng(31.20588470349843, 29.91779871421426)
        map.addMarker(MarkerOptions().position(alexandria).title("Marker in Sydney"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(alexandria, 6f))
        map.uiSettings.apply {
            isZoomControlsEnabled = true
        }
        lifecycleScope.launch {
            addPolyline()
        }
        map.setOnPolylineClickListener(this)
    }

    private suspend fun addPolyline() {
        val polyline = map.addPolyline(
            PolylineOptions().apply {
                add(alexandria, cairo, mansoura)
                width(5f)
                color(Color.BLUE)
                geodesic(true)
                clickable(true)
            }
        )
        delay(3000)
        val newLocationsList = listOf<LatLng>(
            alexandria, cyprus, lebanon
        )
        polyline.points = newLocationsList
    }

    override fun onPolylineClick(p0: Polyline) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
    }
}