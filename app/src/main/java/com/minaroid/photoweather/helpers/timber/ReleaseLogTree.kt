package com.minaroid.photoweather.helpers.timber

import android.util.Log
import timber.log.Timber

class ReleaseLogTree : Timber.Tree() {

    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return !(priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO)
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (isLoggable(tag, priority)) {
            if (priority == Log.ERROR) {
                // TODO => send error to firebase
               Log.d("ReleaseLogTree==>",t?.message?:"")
            }
        }
    }
}