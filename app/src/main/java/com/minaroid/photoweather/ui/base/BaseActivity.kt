package com.minaroid.photoweather.ui.base

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.hadilq.liveevent.LiveEvent
import com.minaroid.photoweather.helpers.UiHelper
import com.minaroid.photoweather.ui.home.HomeViewModel
import javax.inject.Inject
import kotlin.reflect.KClass
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var uiHelper: UiHelper

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutView())
        initViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    protected abstract fun getLayoutView(): View
    protected abstract fun initViews()
    protected abstract fun initViewModel()
    protected abstract fun loadData()

    fun addToDisposable(disposable: Disposable) {
        this.disposable.add(disposable)
    }

    fun subscribeToViewModelObservables(viewModel: BaseViewModel) {
        viewModel.errorMsgLiveData.observe(this, Observer {
            it?.let {
                uiHelper.showErrorMsg(it)
            }
        })

        viewModel.successMsgLiveData.observe(this, Observer {
            it?.let {
                uiHelper.showSuccessMsg(it)
            }
        })

        viewModel.loadingLiveData.observe(this, Observer {
            it?.let {
                if (it)
                    uiHelper.showLoading()
                else uiHelper.hideLoading()
            }
        })
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }

}