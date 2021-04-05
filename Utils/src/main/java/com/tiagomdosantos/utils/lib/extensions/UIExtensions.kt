package com.tiagomdosantos.utils.lib.extensions

import android.content.res.Resources.getSystem
import android.graphics.Point
import android.util.DisplayMetrics
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity

fun Number.convertDpToPixel(): Int {
    return (this.toFloat() * getSystem().displayMetrics.density).toInt()
}

fun Number.convertPxToDp(): Int {
    return (this.toFloat() / getSystem().displayMetrics.density).toInt()
}

fun convertToInt(
    unit: Int = TypedValue.COMPLEX_UNIT_DIP,
    size: Number,
    displayMetrics: DisplayMetrics = getSystem().displayMetrics
): Int {
    return convertTo(unit, size, displayMetrics).toInt()
}

private fun convertTo(
    unit: Int = TypedValue.COMPLEX_UNIT_DIP,
    size: Number,
    displayMetrics: DisplayMetrics = getSystem().displayMetrics
): Number {
    return TypedValue.applyDimension(unit, size.toFloat(), displayMetrics)
}

fun AppCompatActivity.getScreenHeight(): Int {
    val size = Point()
    windowManager.defaultDisplay.getSize(size)
    return size.y
}

fun AppCompatActivity.getScreenWidth(): Int {
    val size = Point()
    windowManager.defaultDisplay.getSize(size)
    return size.x
}

fun AppCompatActivity.width(): Int {
    return getDisplayMetrics().widthPixels
}

fun AppCompatActivity.height(): Int {
    return getDisplayMetrics().heightPixels
}

fun AppCompatActivity.getDisplayMetrics(): DisplayMetrics {
    return DisplayMetrics().apply { windowManager.defaultDisplay.getMetrics(this) }
}
