package com.minaroid.photoweather.ui.home


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.minaroid.photoweather.R
import com.minaroid.photoweather.data.models.image.ImageModel
import com.minaroid.photoweather.databinding.ActivityHomeBinding
import com.minaroid.photoweather.ui.addphoto.AddPhotoActivity
import com.minaroid.photoweather.ui.base.BaseActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.hilt.android.AndroidEntryPoint
import droidninja.filepicker.FilePickerBuilder
import droidninja.filepicker.FilePickerConst
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    @Inject
    lateinit var imagesAdapter: ImagesAdapter

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding
    private val pickerSelectedPaths: MutableList<Uri> = ArrayList()

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.getParcelableArrayListExtra<Uri>(FilePickerConst.KEY_SELECTED_MEDIA)
                    ?.let {
                        if (it.isNotEmpty()) {
                            val addPhotoIntent = Intent(this, AddPhotoActivity::class.java)
                            addPhotoIntent.putExtra(AddPhotoActivity.IMAGE_PATH_KEY, it[0])
                            startActivity(addPhotoIntent)
                        }
                    }
            }
        }

    override fun getLayoutView(): View {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initViews() {
        setSupportActionBar(binding.toolbar)
        binding.imagesRecycler.adapter = imagesAdapter
        binding.addNewBtn.setOnClickListener { openImagePicker() }
    }

    override fun initViewModel() {
        subscribeToViewModelObservables(viewModel)
    }

    private fun openImagePicker() {

        addToDisposable(
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

    override fun loadData() {
    }
}