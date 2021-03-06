package com.minaroid.photoweather.di

import android.content.Context
import com.minaroid.photoweather.helpers.ResourcesHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideResourcesHelper(@ApplicationContext context: Context): ResourcesHelper {
        return ResourcesHelper(context)
    }

}