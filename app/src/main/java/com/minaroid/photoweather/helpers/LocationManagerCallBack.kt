package com.minaroid.photoweather.helpers

import android.location.Location

interface LocationManagerCallBack {

    fun onLocationRetrieved(location: Location?)
}