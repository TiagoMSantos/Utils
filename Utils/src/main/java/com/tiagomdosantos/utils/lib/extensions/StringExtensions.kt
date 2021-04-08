package com.tiagomdosantos.utils.lib.extensions

fun isNumeric(charSequence: CharSequence): Boolean {
    if (charSequence.isEmpty()) {
        return false
    }
    val size = charSequence.length
    for (i in 0 until size) {
        if (!Character.isDigit(charSequence[i])) {
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
