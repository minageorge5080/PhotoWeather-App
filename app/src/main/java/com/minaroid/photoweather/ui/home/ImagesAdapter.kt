package com.minaroid.photoweather.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.minaroid.photoweather.R
import com.minaroid.photoweather.data.models.image.ImageModel
import com.minaroid.photoweather.databinding.ItemImageBinding
import com.minaroid.photoweather.ui.base.BaseAdapter
import com.minaroid.photoweather.ui.base.BaseBindingViewHolder
import javax.inject.Inject

class ImagesAdapter @Inject constructor() : BaseAdapter<ImageModel, ImagesAdapter.ImageViewHolder>() {

    override fun getViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return  ImageViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_image, parent, false))
    }

    inner class ImageViewHolder(private val imageBinding: ItemImageBinding) :
        BaseBindingViewHolder<ImageModel>(imageBinding) {

        override fun bindItem(item: ImageModel?, position: Int) {

        }

    }


}