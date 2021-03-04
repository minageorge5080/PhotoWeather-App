package com.minaroid.photoweather.ui.home


import android.view.View
import androidx.activity.viewModels
import com.minaroid.photoweather.databinding.ActivityHomeBinding
import com.minaroid.photoweather.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding

    override fun getLayoutView(): View {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initViews() {
        setSupportActionBar(binding.toolbar)
    }

    override fun initViewModel() {
        subscribeToViewModelObservables(viewModel)
    }

    override fun loadData() {

    }
}