package com.tiagomdosantos.utils.lib.extensions

import android.widget.TextView

fun TextView.hideValue() {
    this.text = "*****"
}

fun TextView.drawableEnd(layoutId: Int) {
    this.setCompoundDrawablesWithIntrinsicBounds(
        0,
        0,
        layoutId,
        0
    )
}
