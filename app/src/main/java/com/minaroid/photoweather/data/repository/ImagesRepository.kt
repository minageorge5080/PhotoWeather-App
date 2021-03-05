package com.minaroid.photoweather.data.repository

import com.minaroid.photoweather.data.database.PhotoWeatherDB
import com.minaroid.photoweather.data.models.image.ImageMapper
import com.minaroid.photoweather.data.models.image.ImageModel
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class ImagesRepository @Inject constructor(
    private val photoWeatherDB: PhotoWeatherDB,
    private val imageMapper: ImageMapper
) {

    fun insertImage(model: ImageModel): Single<Long> {
        return photoWeatherDB.getImageDao().insert(imageMapper.toImageEntity(model))
    }

    fun deleteImage(model: ImageModel): Single<Int> {
        return photoWeatherDB.getImageDao().deleteImageById(model.id)
    }

    fun getImage(id: Long): Single<ImageModel> {
        return photoWeatherDB.getImageDao().getImageById(id)
            .map{imageMapper.toImageModel(it)}
    }

    fun getAllImages(): Flowable<List<ImageModel>> {
        return photoWeatherDB.getImageDao().getAllImages().map {
            it.map { imageMapper.toImageModel(it) }
        }
    }

}