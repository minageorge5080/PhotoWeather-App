package com.minaroid.photoweather.data.models.image

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "images_table")
data class ImageEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var path: String
)

data class ImageModel(val id : Long = 0 ,val path: String) : Serializable