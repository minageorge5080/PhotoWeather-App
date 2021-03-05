package com.minaroid.photoweather.helpers

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import javax.inject.Inject

class ResourcesHelper @Inject constructor(val context: Context){

    fun getString(@StringRes resourceId: Int): String {
        return context.getString(resourceId)
    }

    fun getColor(@ColorRes colorRes: Int): Int {
        return ContextCompat.getColor(context, colorRes)
    }

    fun getDrawable(@DrawableRes resourceId: Int): Drawable? {
        return ContextCompat.getDrawable(context, resourceId)
    }
}