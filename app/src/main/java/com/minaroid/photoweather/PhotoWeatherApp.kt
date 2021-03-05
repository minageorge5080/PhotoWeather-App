package com.minaroid.photoweather

import android.app.Application
import com.minaroid.photoweather.helpers.timber.DebugLogTree
import com.minaroid.photoweather.helpers.timber.ReleaseLogTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class PhotoWeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setupTimberTree()
    }

    private fun setupTimberTree() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugLogTree())
        } else {
            Timber.plant(ReleaseLogTree())
        }
    }
}