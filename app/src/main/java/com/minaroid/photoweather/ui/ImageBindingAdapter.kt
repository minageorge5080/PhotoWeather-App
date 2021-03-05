package com.minaroid.photoweather.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.minaroid.photoweather.R

@BindingAdapter("loadLocalImage")
fun loadImage(imageView: ImageView, path: String) {

    Glide.with(imageView.context)
        .load(path)
        .placeholder(R.color.bg_gray)
        .into(imageView)
}