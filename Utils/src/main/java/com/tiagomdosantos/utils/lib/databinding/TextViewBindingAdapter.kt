package com.tiagomdosantos.utils.lib.databinding

import android.widget.TextView
import androidx.annotation.FontRes
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter

object TextViewBindingAdapter {
// TODO:
//    @JvmStatic
//    @BindingAdapter("textCapitalized")
//    fun TextView.setCapitalizedText(textToCapitalize: String, locale: Locale) {
//        this.text = takeIf { textToCapitalize.isNotBlank() }?.run {
//            textToCapitalize.substring(0, 1)
//                .toUpperCase(locale) + textToCapitalize.toLowerCase(
//                locale
//            ).substring(1)
//        }
//    }

    @JvmStatic
    @BindingAdapter("fontFamily")
    fun TextView.setFontFamily(@FontRes fontFamily: Int) {
        this.typeface = ResourcesCompat.getFont(this.context, fontFamily)
    }
}
