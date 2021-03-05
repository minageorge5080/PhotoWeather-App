package com.minaroid.photoweather.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.minaroid.photoweather.data.models.image.ImageEntity
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface ImagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(image: ImageEntity): Single<Long>

    @Query("select * from images_table order by id desc")
    fun getAllImages(): Flowable<List<ImageEntity>>

    @Query("Delete from images_table WHERE id = :id")
    fun deleteImageById(id: Long): Single<Int>

    @Query("select * from images_table WHERE id = :id")
    fun getImageById(id: Long): Single<ImageEntity>
}