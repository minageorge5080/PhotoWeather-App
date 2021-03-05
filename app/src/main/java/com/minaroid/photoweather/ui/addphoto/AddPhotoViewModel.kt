package com.minaroid.photoweather.ui.addphoto

import android.location.Location
import android.util.Log
import android.view.View
import androidx.core.view.drawToBitmap
import androidx.lifecycle.MutableLiveData
import com.minaroid.photoweather.data.models.weather.WeatherModel
import com.minaroid.photoweather.data.repository.WeatherRepository
import com.minaroid.photoweather.helpers.FileHelper
import com.minaroid.photoweather.helpers.UiHelper
import com.minaroid.photoweather.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class AddPhotoViewModel @Inject constructor(private val weatherRepository: WeatherRepository) : BaseViewModel() {

    val weatherLiveData = MutableLiveData<WeatherModel>()

    fun getWeatherData(location: Location){
        addToDisposable(weatherRepository.getCurrentWeather(location.latitude,location.longitude)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loadingLiveData.value = true }
            .doFinally { loadingLiveData.value = false }
            .subscribe({
                weatherLiveData.value = it
            },this::processError))
    }

    fun saveImage(image: View) {
        addToDisposable(
            Single.just(image)
                .map { it.drawToBitmap() }
                .map { FileHelper.saveBitmap(it) }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loadingLiveData.value = true }
                .doFinally { loadingLiveData.value = false }
                .subscribe({
                    successMsgLiveData.value = it
                }, Timber::e)
        )
    }
}