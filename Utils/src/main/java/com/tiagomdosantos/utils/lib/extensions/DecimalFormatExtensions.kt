package com.tiagomdosantos.utils.lib.extensions

import java.text.DecimalFormat

fun getDecimalFormat(): DecimalFormat {
    val df = DecimalFormat("#,##0.00;-#,##0.00")
    val dfs = df.decimalFormatSymbols
    dfs.decimalSeparator = ','
    dfs.groupingSeparator = '.'
    df.decimalFormatSymbols = dfs

    return df
}

fun getDecimalFormatWithCurrency(): DecimalFormat {
    val df = DecimalFormat("R$ #,##0.00;-R$ #,##0.00")
    val dfs = df.decimalFormatSymbols
    dfs.decimalSeparator = ','
    dfs.groupingSeparator = '.'
    df.decimalFormatSymbols = dfs

    return df
}

fun getDecimalFormatTruncate(): DecimalFormat {
    val df = DecimalFormat("#,##0;-#,##0")
    val dfs = df.decimalFormatSymbols
    dfs.groupingSeparator = '.'
    df.decimalFormatSymbols = dfs

    return df
}

fun getDecimalFormatWithCurrencyTruncate(): DecimalFormat {
    val df = DecimalFormat("R$ #,##0;-R$ #,##0")
    val dfs = df.decimalFormatSymbols
    dfs.groupingSeparator = '.'
    df.decimalFormatSymbols = dfs

    return df
}
