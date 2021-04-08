package com.tiagomdosantos.utils.lib.extensions

fun Int.centsToDollars(): Double = this.toDouble() / 100.0

fun Int.centsToDollarsFormat(currency: String): String {
    val dollars = this / 100
    val cents = this % 100
    return String.format(getLocaleUS(), "%s%d.%02d", currency, dollars, cents)
}
