package com.tiagomdosantos.utils.lib.extensions

fun CharSequence.isNumeric(): Boolean {
    if (this.isEmpty()) {
        return false
    }
    val size = this.length
    for (i in 0 until size) {
        if (!Character.isDigit(this[i])) {
            return false
        }
    }
    return true
}

fun String.containsDigit(): Boolean {
    return matches(Regex(".*[0-9].*"))
}

fun String.isAlphanumeric(): Boolean {
    return matches(Regex("[a-zA-Z0-9]*"))
}
