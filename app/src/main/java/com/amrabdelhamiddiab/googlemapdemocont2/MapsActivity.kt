package com.amrabdelhamiddiab.googlemapdemocont2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.amrabdelhamiddiab.googlemapdemocont2.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.Marker

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    companion object {
        const val TAG = "MapsActivity"
    }

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private val typeAndStyle by lazy {
        TypeAndStyle()
    }
    private val cameraAndViewPort by lazy {
        CameraAndViewPort()
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.maps_types_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        typeAndStyle.setMapType(item, map)
        return true
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val alexandria = LatLng(31.205297328351207, 29.918485040419384)
        val alexMarker =
            map.addMarker(
                MarkerOptions()
                    .position(alexandria)
                    .title("Marker in Alexandria")
                    .snippet("some random text")
            )
        alexMarker?.tag = "Restaurant"
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(alexandria, 10f))

        //  map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewPort.alexandria))
        map.uiSettings.apply {
            isZoomControlsEnabled = true
        }
        typeAndStyle.setMapStyle(map, this)
        map.setOnMarkerClickListener(this)
        map.setInfoWindowAdapter(CustomInfoAdapter(this))
    }
    override fun onMarkerClick(p0: Marker): Boolean {
        map.animateCamera(CameraUpdateFactory.zoomTo(17f), 2000, null)
        p0.showInfoWindow()
        return true
    }
}