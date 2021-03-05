package com.minaroid.photoweather.helpers

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import androidx.core.content.FileProvider
import com.minaroid.photoweather.R
import java.io.File

object IntentHelper {

    @SuppressLint("QueryPermissionsNeeded")
    fun shareImage(context: Context, path: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/*"
        val file = File(path)
        val uri = FileProvider.getUriForFile(context, context.applicationContext.packageName + ".provider", file)
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        val chooserDialog = Intent.createChooser(intent, context.getString(R.string.share_dialog))
        val resInfoList: List<ResolveInfo> = context.applicationContext.packageManager.queryIntentActivities(chooserDialog, PackageManager.MATCH_DEFAULT_ONLY)
        for (resolveInfo in resInfoList) {
            val packageName = resolveInfo.activityInfo.packageName
            context.grantUriPermission(
                packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        }
        context.startActivity(chooserDialog)
    }
}