package com.minaroid.photoweather.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.minaroid.photoweather.R
import com.minaroid.photoweather.data.models.image.ImageModel
import com.minaroid.photoweather.databinding.ItemImageBinding
import javax.inject.Inject

class ImagesAdapter @Inject constructor() :
    ListAdapter<ImageModel, ImagesAdapter.ImageViewHolder>(DataDiffCallback()) {

    var onDeleteClickedListener: ((item: ImageModel) -> Unit)? = null
    var onShareClickedListener: ((item: ImageModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_image,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    inner class ImageViewHolder(private val imageBinding: ItemImageBinding) :
        RecyclerView.ViewHolder(imageBinding.root) {
        lateinit var image: ImageModel

        init {

            imageBinding.delete.setOnClickListener {
                onDeleteClickedListener?.invoke(image)
            }

            imageBinding.share.setOnClickListener {
                onShareClickedListener?.invoke(image)
            }
        }

        fun bindItem(item: ImageModel) {
            this.image = item
            imageBinding.imageModel = item
        }
    }

    class DataDiffCallback : DiffUtil.ItemCallback<ImageModel>() {
        override fun areItemsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
            return oldItem == newItem
        }
    }


}