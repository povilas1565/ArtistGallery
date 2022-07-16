package com.example.artgallery.presentation.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.withStyledAttributes
import com.example.artgallery.R

class LoaderButton @JvmOverloads constructor(
    context:Context,
    attributeSet: AttributeSet? = null
) : FrameLayout(
    context,
    attributeSet
) {

    private val binding =
        LoaderButtonViewBinding.inflats(LayoutInflater.from(context), this)
    var text: String = ""
        set(value) {
            field = value
            binding.textTv.text = value
        }

    var isLoading: Boolean = false
         set(value) {
             field = value
             binding.progreesBar.isVisible = value
             binding.textTv.isVisible = !value
             binding.root.isEnabled = !value
         }

    init {
        context.withStyledAttributes(attributeSet, R.styleable.LoaderButton) {
        text = getString(R.styleable.LoaderButton_textBtn) ?: ""
            isLoading = getBoolean(R.styleable.LoaderButton_isLoading, false)
        }
    }

}


