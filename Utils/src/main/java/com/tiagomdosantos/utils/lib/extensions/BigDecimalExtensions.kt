package com.tiagomdosantos.utils.lib.extensions

import java.math.BigDecimal
import java.text.DecimalFormat

fun BigDecimal.stripZeros(): String {
    return DecimalFormat("0.##").format(this).replace(".", ",")
}

fun BigDecimal.greaterThanZero(): Boolean {
    return this > BigDecimal.ZERO && this > BigDecimal("0.0")
}

fun BigDecimal.isZero(): Boolean {
    return this == BigDecimal.ZERO || this == BigDecimal("0.0")
}
