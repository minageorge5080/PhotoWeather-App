package com.minaroid.photoweather.customviews

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.TextViewCompat
import com.minaroid.photoweather.R
import com.minaroid.photoweather.helpers.DisplayHelper


class CenteredTitleToolbar(context: Context, attrs: AttributeSet) : Toolbar(context, attrs) {

    private var _titleTextView: AppCompatTextView? = null
    private var _screenWidth: Int = 0
    private var _centerTitle = true
    private var _currentTitle = ""
    private val location = IntArray(2)

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CenteredTitleToolbar)
        _centerTitle = typedArray.getBoolean(R.styleable.CenteredTitleToolbar_centerTitle, true)
        _currentTitle = typedArray.getString(R.styleable.CenteredTitleToolbar_title) ?: ""
        typedArray.recycle()
        _screenWidth = DisplayHelper.getDisplayPixelWidth(context)
        _titleTextView = AppCompatTextView(context)
        _titleTextView?.maxLines = 1
        _titleTextView?.ellipsize = TextUtils.TruncateAt.END
        addView(_titleTextView)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        _titleTextView?.let {
            title = _currentTitle
            TextViewCompat.setTextAppearance(it, R.style.ToolbarTheme)
        }

        if (_centerTitle) {
            _titleTextView?.getLocationOnScreen(location)
            _titleTextView?.translationX = _titleTextView?.translationX?.plus(
                (-location[0] + _screenWidth / 2 - (_titleTextView?.width?.div(2) ?: 0))
            ) ?: 0f

        }
    }

    override fun setTitle(title: CharSequence?) {
        _titleTextView?.text = title ?: ""
        _currentTitle = title?.toString() ?: ""
        requestLayout()
    }

    override fun setTitle(titleRes: Int) {
        _titleTextView?.setText(titleRes)
        _currentTitle = title?.toString() ?: ""
        requestLayout()
    }

    fun setTitleCentered(centered: Boolean) {
        _centerTitle = centered
        requestLayout()
    }

}
