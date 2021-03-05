package com.minaroid.photoweather.ui.imagepreview

import androidx.lifecycle.MutableLiveData
import com.minaroid.photoweather.data.models.image.ImageModel
import com.minaroid.photoweather.data.repository.ImagesRepository
import com.minaroid.photoweather.helpers.FileHelper
import com.minaroid.photoweather.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ImagePreviewViewModel @Inject constructor(private val imagesRepository: ImagesRepository) :
    BaseViewModel() {

    fun deleteImage(image: ImageModel) {
        addToDisposable(imagesRepository.deleteImage(image)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loadingLiveData.value = true }
            .doFinally { loadingLiveData.value = false }
            .subscribe({
                FileHelper.deleteImage(image.path)
                finishScreenLiveData.value = true
            }, Timber::e)
        )
    }
}