package com.minaroid.photoweather.ui.base

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding

abstract class BaseBindingViewHolder<I>(val viewDataBinding: ViewDataBinding) : BaseViewHolder<I>(viewDataBinding.root) {

    var layoutParams: ViewGroup.LayoutParams = itemView.layoutParams

    fun clearAnimation() {
        itemView.clearAnimation()
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <T : ViewDataBinding> bind(binding: T.() -> Unit) {
        binding(viewDataBinding as T)
        viewDataBinding.executePendingBindings()
    }
}