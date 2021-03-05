package com.minaroid.photoweather.ui.addphoto

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.minaroid.photoweather.R
import com.minaroid.photoweather.databinding.ActivityAddPhotoBinding
import com.minaroid.photoweather.ui.base.BaseActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.hilt.android.AndroidEntryPoint
import droidninja.filepicker.FilePickerBuilder
import droidninja.filepicker.FilePickerConst
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

@AndroidEntryPoint
class AddPhotoActivity : BaseActivity() {

    private val viewModel: AddPhotoViewModel by viewModels()
    private lateinit var binding: ActivityAddPhotoBinding
    private val pickerSelectedPaths: MutableList<Uri> = ArrayList()
    private val disposable = CompositeDisposable()

    override fun getLayoutView(): View {
        binding = ActivityAddPhotoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initViews() {

    }

    override fun initViewModel() {
        openImagePicker()
    }

    override fun loadData() {

    }

    private fun openImagePicker() {
        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    uiHelper.showSuccessMsg("DONE")
                }
            }

        disposable.add(
            RxPermissions(this)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .subscribe({
                    if (it) {
                        val intent = FilePickerBuilder.instance
                            .setMaxCount(1)
                            .setSelectedFiles(pickerSelectedPaths as java.util.ArrayList<Uri>)
                            .setActivityTitle(getString(R.string.file_picker_label))
                            .enableCameraSupport(true)
                            .setActivityTheme(R.style.FilePickerTheme)
                            .showFolderView(false)
                            .enableImagePicker(true)
                            .build(FilePickerConst.MEDIA_PICKER, this)
                        resultLauncher.launch(intent)
                    }
                }, Timber::e)
        )

    }

    companion object {

        private const val REQUEST_CODE_PICKER = 130

    }
}