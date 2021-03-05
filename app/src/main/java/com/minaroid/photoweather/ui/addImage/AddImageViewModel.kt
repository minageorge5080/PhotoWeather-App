package com.minaroid.photoweather.ui.addImage

import android.location.Location
import android.view.View
import androidx.core.view.drawToBitmap
import androidx.lifecycle.MutableLiveData
import com.minaroid.photoweather.data.models.image.ImageModel
import com.minaroid.photoweather.data.models.weather.WeatherModel
import com.minaroid.photoweather.data.repository.ImagesRepository
import com.minaroid.photoweather.data.repository.WeatherRepository
import com.minaroid.photoweather.helpers.FileHelper
import com.minaroid.photoweather.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AddImageViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val imagesRepository: ImagesRepository
) : BaseViewModel() {

    val weatherLiveData = MutableLiveData<WeatherModel>()
    val imageAddedLiveData = MutableLiveData<ImageModel>()

    fun getWeatherData(location: Location) {
        addToDisposable(weatherRepository.getCurrentWeather(location.latitude, location.longitude)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loadingLiveData.value = true }
            .doFinally { loadingLiveData.value = false }
            .subscribe({
                weatherLiveData.value = it
            }, this::processError)
        )
    }

    fun saveImage(image: View) {
        addToDisposable(
            Single.just(image)
                .map { it.drawToBitmap() }
                .map { FileHelper.saveBitmap(it) }
                .flatMap { imagesRepository.insertImage(ImageModel(0, it)) }
                .flatMap { imagesRepository.getImage(it) }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loadingLiveData.value = true }
                .doFinally { loadingLiveData.value = false }
                .subscribe({
                    imageAddedLiveData.value = it
                }, Timber::e)
        )
    }
}