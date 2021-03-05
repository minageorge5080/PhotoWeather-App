package com.minaroid.photoweather.ui.base

import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.minaroid.photoweather.R
import com.minaroid.photoweather.helpers.ResourcesHelper
import com.minaroid.photoweather.helpers.UiHelper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.json.JSONObject
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

open class BaseViewModel : ViewModel() {

    @Inject
    lateinit var resourcesHelper: ResourcesHelper
    private val disposable = CompositeDisposable()
    val errorMsgLiveData: LiveEvent<String> = LiveEvent()
    val successMsgLiveData: LiveEvent<String> = LiveEvent()
    val loadingLiveData: LiveEvent<Boolean> = LiveEvent()

    fun addToDisposable(disposable: Disposable) {
        this.disposable.add(disposable)
    }

    fun processError(throwable: Throwable) {
        Timber.e(throwable)
        when (throwable) {
            is IOException -> {
                errorMsgLiveData.value = resourcesHelper.getString(R.string.msg_connection_error)
            }
            is HttpException -> {
                errorMsgLiveData.value = getHttpErrorMessage(throwable)
            }

            else -> {
                errorMsgLiveData.value = resourcesHelper.getString(R.string.msg_internal_error)
            }
        }
    }

    private fun getHttpErrorMessage(throwable: HttpException): String {
        var msg = resourcesHelper.getString(R.string.msg_internal_error)
        val jsonObject = JSONObject(throwable.response().errorBody()?.string()?:"{}")
        if(jsonObject.has("message")){
            msg = jsonObject.getString("message")
        }
        return msg
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}