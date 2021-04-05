package com.tiagomdosantos.utils.lib.extensions

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt

fun Drawable?.setSize(width: Number, height: Number): Drawable? {
    return this?.mutate()
        ?.apply {
            val resizeWidth = convertToInt(size = width).takeIf { it > 0 }
                ?: intrinsicWidth

            val resizeHeight = convertToInt(size = height).takeIf { it > 0 }
                ?: intrinsicHeight

            setBounds(0, 0, resizeWidth, resizeHeight)
        }
}

fun Drawable?.setColor(
    @ColorInt color: Int,
    mode: PorterDuff.Mode = PorterDuff.Mode.SRC_IN
): Drawable? {
    return this?.mutate()
        ?.apply {
            colorFilter = PorterDuffColorFilter(color, mode)
        }
}
