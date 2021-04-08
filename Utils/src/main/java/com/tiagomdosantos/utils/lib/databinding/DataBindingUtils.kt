package com.tiagomdosantos.utils.lib.databinding

import android.graphics.drawable.BitmapDrawable
import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.tiagomdosantos.utils.lib.builders.BlurBuilder

object DataBindingUtils {
    @JvmStatic
    @BindingAdapter("errorEnabled")
    fun TextInputLayout.setErrorEnabled(errorEnabled: Boolean?) {
        isErrorEnabled = errorEnabled ?: false
    }

    @JvmStatic
    @BindingAdapter("error")
    fun TextInputLayout.setTextInputLayoutError(errorString: String) {
        error = errorString
    }

    @JvmStatic
    @BindingAdapter("blur")
    fun View.applyBlur(apply: Boolean) {
        if (!apply) {
            background = null
        } else {
            background = try {
                val bitmap = BlurBuilder.applyBlur(context, parent as View)
                BitmapDrawable(context.resources, bitmap)
            } catch (e: Exception) {
                null
            }
        }
    }
}
