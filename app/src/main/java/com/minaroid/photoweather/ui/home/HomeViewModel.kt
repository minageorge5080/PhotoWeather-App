package com.minaroid.photoweather.ui.home

import android.util.Log
import com.minaroid.photoweather.data.repository.WeatherRepository
import com.minaroid.photoweather.helpers.UiHelper
import com.minaroid.photoweather.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class HomeViewModel @Inject constructor(val weatherRepository: WeatherRepository) : BaseViewModel() {

    fun showSuccessMsg(msg: String) {
        loadingLiveData.value = true
    }
}