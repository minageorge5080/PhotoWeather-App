package com.minaroid.photoweather.data.models.image

import javax.inject.Inject

class ImageMapper @Inject constructor() {

    fun toImageEntity(model: ImageModel): ImageEntity {
        return ImageEntity(model.id, model.path)
    }

    fun toImageModel(entity: ImageEntity): ImageModel {
        return ImageModel(entity.id, entity.path)
    }
}