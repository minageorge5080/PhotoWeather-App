package com.minaroid.photoweather.helpers

import android.app.Dialog
import android.content.Context
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.annotation.NonNull
import com.minaroid.photoweather.R
import javax.inject.Inject

class UiHelper @Inject constructor(val context: Context) {

    private var progressDialog: Dialog? = null


    fun showSuccessMsg(@NonNull msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun showErrorMsg(@NonNull msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun showLoading() {
        progressDialog?.show() ?: initProgressDialog()
    }

    fun hideLoading() {
        progressDialog?.dismiss()
    }

    private fun initProgressDialog() {
        progressDialog = Dialog(context,R.style.DialogStyle)
        progressDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog?.setCancelable(false)
        progressDialog?.setCanceledOnTouchOutside(false)
        progressDialog?.setContentView(R.layout.layout_progress_dialog)
        progressDialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        progressDialog?.show()
    }

}