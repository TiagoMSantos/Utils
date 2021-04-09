package com.tiagomdosantos.utils.lib.databinding

import android.widget.TextView
import androidx.annotation.FontRes
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import java.util.*

object TextViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("textCapitalized")
    fun TextView.setCapitalizedText(textToCapitalize: String) {
        this.text = takeIf { textToCapitalize.isNotBlank() }?.run {
            textToCapitalize.substring(0, 1)
                .toUpperCase(Locale.ROOT) + textToCapitalize.toLowerCase(
                Locale.ROOT
            ).substring(1)
        }
    }

    @JvmStatic
    @BindingAdapter("fontFamily")
    fun TextView.setFontFamily(@FontRes fontFamily: Int) {
        this.typeface = ResourcesCompat.getFont(this.context, fontFamily)
    }
}
