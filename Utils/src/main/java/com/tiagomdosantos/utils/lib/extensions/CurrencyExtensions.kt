package com.tiagomdosantos.utils.lib.extensions

import java.math.BigDecimal

fun formatCurrency(value: String): String {
    return formatCurrency(BigDecimal(value.replace(".", "").replace(",", ".")))
}

fun formatCurrency(value: Float): String {
    return formatCurrency(BigDecimal.valueOf(value.toDouble()))
}

fun formatCurrency(value: Double): String {
    return formatCurrency(BigDecimal.valueOf(value))
}

fun formatCurrency(value: BigDecimal): String {
    return try {
        getDecimalFormatWithCurrency().format(value)
    } catch (e: Exception) {
        return ""
    }
}

fun formatCurrencyWithoutMonetaryFormat(value: Double): String {
    return getDecimalFormat().format(value)
}

fun formatCurrencyWithoutMonetaryFormat(value: String): String? {
    if (isNotNullOrEmpty(value) || !isNumeric(value))
        return value

    return getDecimalFormat().format(value.replace(".", "").replace(",", ".").replace("R$ ", ""))
}

fun getCustomizedBalance(balance: String): String {
    return StringBuilder(balance)
        .insert(balance.length - 3, "#")
        .insert(balance.length + 1, "#")
        .toString()
}

fun getCustomizedBalance(value: Double): String {
    val balance: String = formatCurrency(value).replace(" ", "")
    return getCustomizedBalance(balance)
}
