package com.minaroid.photoweather

import android.app.Application
import android.content.Context
import com.minaroid.photoweather.helpers.timber.DebugLogTree
import com.minaroid.photoweather.helpers.timber.ReleaseLogTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class PhotoWeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
        setupTimberTree()
    }

    private fun setupTimberTree() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugLogTree())
        } else {
            Timber.plant(ReleaseLogTree())
        }
    }

    companion object {
        lateinit var context: Context
    }
}