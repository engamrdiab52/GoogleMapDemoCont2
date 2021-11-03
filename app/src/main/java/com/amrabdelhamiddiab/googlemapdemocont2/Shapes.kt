package com.amrabdelhamiddiab.googlemapdemocont2

import android.graphics.Color
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.delay

class Shapes {
    private val alexandria = LatLng(31.20588470349843, 29.91779871421426)
    private val cairo = LatLng(30.04640852760722, 31.236832732735163)
    private val mansoura = LatLng(31.04343698358825, 31.378033567808494)
    private val siwa = LatLng(29.280285360336215, 25.5088921201458)
    private val cyprus = LatLng(34.760781889258894, 32.626692560378494)
    private val lebanon = LatLng(34.052888370057616, 35.84241239159556)

    private val a = LatLng(31.60362195102069, 30.08743412702127)
    private val b = LatLng(31.446731709384693, 29.321775243437827)
    private val c = LatLng(31.16899730490087, 30.69379950770655)
    private val d = LatLng(30.736755690156926, 29.623027979219206)

    private val a0 = LatLng(31.40729282184666, 30.06603396375728)
    private val b0 = LatLng(31.21460579868459, 30.11014879307657)
    private val c0 = LatLng(31.122497364600854, 29.891288781265246)
    private val d0 = LatLng(31.260762247366973, 29.7579431811633)


    private suspend fun addPolyline(map: GoogleMap) {
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

    fun addPolygon(map: GoogleMap) {
        val polygon = map.addPolygon(
            PolygonOptions().apply {
                //clockwise direction
                add(d, c, a, b)
                fillColor(R.color.black)
                strokeColor(R.color.black)
                //when two polygons overlap you need the zindex to add click listner to the hightest
                zIndex(1f)
                addHole(listOf(a0, b0, c0, d0))
            }
        )
    }
}