package com.amrabdelhamiddiab.googlemapdemocont2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.amrabdelhamiddiab.googlemapdemocont2.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private val alexandria = LatLng(31.20588470349843, 29.91779871421426)
    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private val shapes by lazy {
        Shapes()
    }

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
        map.addMarker(MarkerOptions().position(alexandria).title("Marker in Sydney"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(alexandria, 10f))
        map.uiSettings.isZoomControlsEnabled = true
        shapes.addPolygon(map)
    }
}