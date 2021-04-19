package com.tiagomdosantos.utils.lib.extensions

fun Int.centsToDollars(): Double = this.toDouble() / 100.0

infix fun Int.centsToDollarsWithFormat(format: String): String {
    val dollars = this / 100
    val cents = this % 100
    return String.format(getLocaleUS(), "%s%d.%02d", format, dollars, cents)
}
