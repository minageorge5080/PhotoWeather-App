package com.minaroid.photoweather.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.minaroid.photoweather.R
import com.minaroid.photoweather.data.remote.ApiService
import com.minaroid.photoweather.databinding.ActivityHomeBinding
import com.minaroid.photoweather.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding

    override fun getLayoutView(): View {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initActivityViews() {
        setSupportActionBar(binding.toolbar)
    }

}