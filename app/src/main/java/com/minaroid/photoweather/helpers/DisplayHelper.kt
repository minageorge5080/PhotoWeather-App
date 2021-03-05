package com.minaroid.photoweather.helpers

import android.content.Context
import android.content.res.Configuration
import android.graphics.Point
import android.util.TypedValue
import android.view.Window
import android.view.WindowManager

object DisplayHelper {

    private fun getDisplayPixelSize(context: Context): Point {

        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            val display = context.display
            val size = Point()
            display?.getRealSize(size)
            size
        } else {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

            @Suppress("DEPRECATION")
            val display = wm.defaultDisplay
            val size = Point()
            @Suppress("DEPRECATION")
            display.getSize(size)
            size
        }
    }

    fun getDisplayPixelWidth(context: Context): Int {
        val size = getDisplayPixelSize(context)
        return size.x
    }

}