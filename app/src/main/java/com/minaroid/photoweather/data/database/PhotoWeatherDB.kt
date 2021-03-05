package com.minaroid.photoweather.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.minaroid.photoweather.data.models.image.ImageEntity

@Database(entities = [ImageEntity::class], version = 1, exportSchema = false)

abstract class PhotoWeatherDB : RoomDatabase() {


    abstract fun getImageDao(): ImagesDao

    companion object {
        @Volatile
        private var instance: PhotoWeatherDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context): PhotoWeatherDB {
            return Room.databaseBuilder(context.applicationContext, PhotoWeatherDB::class.java, "photo_weather.db")
                    .fallbackToDestructiveMigrationOnDowngrade()
                    .build()
        }

    }
}