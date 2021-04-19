package com.tiagomdosantos.utils.lib.extensions

import android.widget.TextView

fun TextView.hideValue() {
    this.text = "*****"
}

infix fun TextView.addDrawableEnd(layoutId: Int) {
    this.setCompoundDrawablesWithIntrinsicBounds(
        0,
        0,
        layoutId,
        0
    )
}
