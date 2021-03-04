package com.minaroid.photoweather

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PhotoWeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()

    }
}