package com.tiagomdosantos.utils.lib.extensions

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat

fun Context.getDrawableWithContext(@DrawableRes drawableRes: Int): Drawable? {
    return try {
        ContextCompat.getDrawable(this, drawableRes)
            ?: VectorDrawableCompat.create(this.resources, drawableRes, null)
    } catch (e: Resources.NotFoundException) {
        null
    }
}

fun Context.getPreferences(name: String, mode: Int = Context.MODE_PRIVATE): SharedPreferences {
    return getSharedPreferences(name, mode)
}

fun Context.getClipboardManager() = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

fun Context.getConnectivityManager() =
    getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

fun Context.copyTextToClipboard(value: String) {
    getClipboardManager().setPrimaryClip(ClipData.newPlainText("text", value))
}

fun Context.copyUriToClipboard(uri: Uri) {
    getClipboardManager().setPrimaryClip(ClipData.newUri(contentResolver, "uri", uri))
}

fun Context.getTextFromClipboard(): CharSequence {
    return getClipboardManager().primaryClip?.let { clipData ->
        takeIf { clipData.itemCount > 0 }?.run {
            clipData.getItemAt(0).coerceToText(this)
        } ?: ""
    } ?: ""
}

fun Context.getUriFromClipboard(): Uri? {
    return getClipboardManager().primaryClip?.let { clipData ->
        takeIf { clipData.itemCount > 0 }?.run {
            clipData.getItemAt(0).uri
        }
    }
}

fun Context.vibrate(duration: Long = 500L) {
    val vib = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        vib.vibrate(VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE))
    } else {
        @Suppress("DEPRECATION")
        vib.vibrate(duration)
    }
}
