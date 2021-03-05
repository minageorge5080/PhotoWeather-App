package com.minaroid.photoweather.ui.imagepreview

import android.view.View
import androidx.activity.viewModels
import com.minaroid.photoweather.R
import com.minaroid.photoweather.data.models.image.ImageModel
import com.minaroid.photoweather.databinding.ActivityImagePreviewBinding
import com.minaroid.photoweather.helpers.IntentHelper
import com.minaroid.photoweather.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImagePreviewActivity : BaseActivity() {

    private val viewModel: ImagePreviewViewModel by viewModels()
    private lateinit var binding: ActivityImagePreviewBinding
    private lateinit var imageModel: ImageModel

    override fun getLayoutView(): View {
        binding = ActivityImagePreviewBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initViews() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_toolbar_back)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        intent.getSerializableExtra(IMAGE_KEY)?.let {
            imageModel = it as ImageModel
            binding.path = imageModel.path
        }
    }

    override fun initViewModel() {
        subscribeToViewModelObservables(viewModel)
    }

    override fun loadData() {

    }

    companion object {
        const val IMAGE_KEY = "image_key"
    }

    fun onDeleteClicked(view :View) {
        viewModel.deleteImage(imageModel)
    }

    fun onShareClicked(view :View) {
        IntentHelper.shareImage(this,imageModel.path)
    }

}