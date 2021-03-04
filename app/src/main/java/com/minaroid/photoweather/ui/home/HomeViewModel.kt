package com.minaroid.photoweather.ui.home

import com.minaroid.photoweather.data.repository.WeatherRepository
import com.minaroid.photoweather.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel  @Inject constructor(weatherRepository: WeatherRepository) : BaseViewModel(){

}