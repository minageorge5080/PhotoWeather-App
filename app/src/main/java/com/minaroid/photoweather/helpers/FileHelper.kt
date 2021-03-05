package com.minaroid.photoweather.helpers

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import com.minaroid.photoweather.PhotoWeatherApp
import timber.log.Timber
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.*

object FileHelper {

    private const val DIR_NAME = "photoWeather"

    fun getAppFolder(context: Context): File {
        val storageDirectory = context.applicationContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        val appFolder = File(storageDirectory, DIR_NAME)

        if (!appFolder.exists()) {
            val wasCreated = appFolder.mkdirs()
            if (!wasCreated) {
                Timber.tag("CapturedImages").e("Failed to create directory")
            }
        }
        return appFolder
    }

    fun saveBitmap(bitmap: Bitmap): String {
        val title = UUID.randomUUID().toString()
        val tempFile = File.createTempFile(title, ".jpg", getAppFolder(PhotoWeatherApp.context))
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val bitmapData: ByteArray = bytes.toByteArray()

        //write the bytes in file
        val fos = FileOutputStream(tempFile)
        fos.write(bitmapData)
        fos.flush()
        fos.close()
        return tempFile.absolutePath
    }

}