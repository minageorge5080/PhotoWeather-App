package com.minaroid.photoweather.ui.addphoto

import android.util.Log
import com.minaroid.photoweather.data.repository.WeatherRepository
import com.minaroid.photoweather.helpers.UiHelper
import com.minaroid.photoweather.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class AddPhotoViewModel @Inject constructor(private val weatherRepository: WeatherRepository) : BaseViewModel() {

    fun getWeatherData(){
        addToDisposable(weatherRepository.getCurrentWeather(29.9407001,31.2806502)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loadingLiveData.value = true }
            .doFinally { loadingLiveData.value = false }
            .subscribe({
                successMsgLiveData.value = it.locationName
            },this::processError))
    }
}