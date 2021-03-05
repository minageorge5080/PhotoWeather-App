package com.minaroid.photoweather.data.models.weather

data class WeatherResponse(val name: String, val sys: SysResponse, val main: MainResponse, val weather: List<WeatherStatus>)

data class SysResponse(val country: String)

data class MainResponse(val temp: Double)

data class WeatherStatus(val description: String)

data class WeatherModel(val locationName: String, val country: String, val temp: Double,val status :String?)






