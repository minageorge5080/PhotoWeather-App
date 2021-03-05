package com.minaroid.photoweather.data.models.weather

import javax.inject.Inject

class WeatherMapper @Inject constructor() {

    fun toWeatherModel(response: WeatherResponse): WeatherModel {

        return WeatherModel(
            response.name,
            response.sys.country,
            response.main.temp,
            response.weather[0].description
        )
    }
}