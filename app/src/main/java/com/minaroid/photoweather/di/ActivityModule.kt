package com.minaroid.photoweather.di

import android.content.Context
import com.minaroid.photoweather.helpers.UiHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @Provides
    fun provideUiHelper(@ActivityContext context: Context) :UiHelper {
        return  UiHelper(context)
    }
}