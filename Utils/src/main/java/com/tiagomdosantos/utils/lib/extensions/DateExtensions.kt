package com.tiagomdosantos.utils.lib.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun getLocaleBR() = Locale("pt", "BR")
fun getTimeZoneBR(): TimeZone = TimeZone.getTimeZone("America/Sao_Paulo")

const val BRAZILIAN_DATE_FORMAT = "dd/MM/yyyy"

fun Date.toFormattedString(format: String, localeTimeZone: TimeZone = getTimeZoneBR()): String {
    return SimpleDateFormat(format, getLocaleBR()).apply {
        timeZone = localeTimeZone
    }.format(this)
}

fun getFormattedCurrentDate(): String {
    return SimpleDateFormat(BRAZILIAN_DATE_FORMAT, getLocaleBR()).format(Date())
}
