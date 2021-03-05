package com.minaroid.photoweather.data.repository

import com.minaroid.photoweather.data.models.weather.WeatherMapper
import com.minaroid.photoweather.data.models.weather.WeatherModel
import com.minaroid.photoweather.data.models.weather.WeatherResponse
import com.minaroid.photoweather.data.remote.ApiService
import com.minaroid.photoweather.data.remote.NetworkConstants
import com.minaroid.photoweather.data.remote.NetworkManager
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val networkManager: NetworkManager, private val weatherMapper: WeatherMapper) {

    fun getCurrentWeather(lat: Double, lon: Double): Single<WeatherModel> {
        val params = HashMap<String, Any>()
        params["lat"] = lat
        params["lon"] = lon

        return networkManager.getRequest(
            api = NetworkConstants.WEATHER,
            params = params,
            parseClass = WeatherResponse::class.java)
            .map {
                weatherMapper.toWeatherModel(it)
            }
    }
}