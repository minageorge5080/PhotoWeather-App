package com.minaroid.photoweather.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.minaroid.photoweather.R
import com.minaroid.photoweather.databinding.ActivityHomeBinding
import com.minaroid.photoweather.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun getLayoutView(): View {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initActivityViews() {
        setSupportActionBar(binding.toolbar)
    }

}