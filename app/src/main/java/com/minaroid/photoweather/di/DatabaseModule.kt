package com.minaroid.photoweather.di

import android.content.Context
import com.minaroid.photoweather.data.database.PhotoWeatherDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): PhotoWeatherDB {
        return PhotoWeatherDB(context)
    }

}