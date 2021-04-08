package com.tiagomdosantos.utils.lib.extensions

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat

fun Context.getDrawableWithContext(@DrawableRes drawableRes: Int): Drawable? {
    return try {
        ContextCompat.getDrawable(this, drawableRes)
            ?: VectorDrawableCompat.create(this.resources, drawableRes, null)
    } catch (e: Resources.NotFoundException) {
        null
    }
}
