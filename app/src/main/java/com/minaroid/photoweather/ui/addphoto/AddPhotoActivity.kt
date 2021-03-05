package com.minaroid.photoweather.ui.addphoto

import android.Manifest
import android.location.Location
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.drawToBitmap
import androidx.lifecycle.Observer
import com.minaroid.photoweather.R
import com.minaroid.photoweather.databinding.ActivityAddPhotoBinding
import com.minaroid.photoweather.helpers.FileHelper
import com.minaroid.photoweather.helpers.LocationManagerCallBack
import com.minaroid.photoweather.helpers.timber.LocationManager
import com.minaroid.photoweather.ui.base.BaseActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*

@AndroidEntryPoint
class AddPhotoActivity : BaseActivity(), LocationManagerCallBack {

    private val viewModel: AddPhotoViewModel by viewModels()
    private lateinit var binding: ActivityAddPhotoBinding
    private var locationManager: LocationManager? = null

    override fun getLayoutView(): View {
        binding = ActivityAddPhotoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initViews() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_toolbar_back)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        intent.getParcelableExtra<Uri>(IMAGE_PATH_KEY)?.let {
            binding.imageView.setImageURI(it)
        }
    }

    override fun initViewModel() {
        subscribeToViewModelObservables(viewModel)
        viewModel.weatherLiveData.observe(this, Observer {
            binding.weatherModel = it
        })
    }

    override fun loadData() {
        if (viewModel.weatherLiveData.value == null) {
            getCurrentLocation()
        }
    }

    private fun getCurrentLocation() {
        addToDisposable(
            RxPermissions(this)
                .request(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
                .subscribe({
                    if (it) {
                        locationManager = LocationManager(this@AddPhotoActivity, this)
                        locationManager?.startLocationUpdates()
                        uiHelper.showLoading()
                    } else {
                        uiHelper.showErrorMsg(getString(R.string.msg_location_permission))
                    }
                }, Timber::e)
        )
    }

    override fun onLocationRetrieved(location: Location?) {
        location?.let {
            locationManager?.stopLocationUpdates()
            viewModel.getWeatherData(it)
        }
    }

    fun onSaveClicked(view: View) {
        viewModel.saveImage(binding.imageContainer)
    }

    companion object {
        const val IMAGE_PATH_KEY = "image_path"
    }


}