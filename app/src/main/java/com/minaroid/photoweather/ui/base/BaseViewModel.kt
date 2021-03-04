package com.minaroid.photoweather.ui.base

import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    private val disposable = CompositeDisposable()
    val errorMsgLiveData: LiveEvent<String> = LiveEvent()
    val successMsgLiveData: LiveEvent<String> = LiveEvent()
    val loadingLiveData: LiveEvent<Boolean> = LiveEvent()

    fun addToDisposable(disposable: Disposable) {
        this.disposable.add(disposable)
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}