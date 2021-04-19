package com.tiagomdosantos.utils.lib.extensions

import java.math.BigDecimal

fun String.formatCurrency(): String {
    return BigDecimal(replace(".", "").replace(",", ".")).formatCurrency()
}

fun Float.formatCurrency(): String {
    return BigDecimal.valueOf(this.toDouble()).formatCurrency()
}

fun Double.formatCurrency(): String {
    return BigDecimal.valueOf(this).formatCurrency()
}

fun BigDecimal.formatCurrency(): String {
    return try {
        getDecimalFormatWithCurrency().format(this)
    } catch (e: Exception) {
        return ""
    }
}

fun Double.formatCurrencyWithoutMonetaryFormat(): String {
    return getDecimalFormat().format(this)
}

fun String.formatCurrencyWithoutMonetaryFormat(): String? {
    if (isNotNullOrEmpty(this) || this.isNumeric().not())
        return this

    return getDecimalFormat().format(this.replace(".", "").replace(",", ".").replace("R$ ", ""))
}

fun String.getCustomizedBalance(): String {
    return StringBuilder(this)
        .insert(this.length - 3, "#")
        .insert(this.length + 1, "#")
        .toString()
}

fun Double.getCustomizedBalance(): String {
    return this.formatCurrency().replace(" ", "").getCustomizedBalance()
}
