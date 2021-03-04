package com.minaroid.photoweather.ui.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutView())
    }

    protected abstract fun getLayoutView(): View

    protected abstract fun initActivityViews()

}