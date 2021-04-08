package com.tiagomdosantos.utils.lib.extensions

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.WindowInsets
import android.view.inputmethod.InputMethodManager
import androidx.annotation.AnimRes
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.tiagomdosantos.utils.lib.R

fun AppCompatActivity.bindingContentView(layout: Int): ViewDataBinding {
    return bindingContentView<ViewDataBinding>(layout)
}

fun <T> AppCompatActivity.bindingContentView(layout: Int): T {
    return DataBindingUtil.setContentView<ViewDataBinding>(this, layout)
        .also { it.lifecycleOwner = this } as T
}

fun Activity.setFullScreen() {
    window.insetsController?.show(WindowInsets.Type.statusBars())
}

fun Activity.showToolbar() {
    actionBar?.show()
}

fun Activity.hideToolbar() {
    actionBar?.hide()
}

fun AppCompatActivity.showToolbar() {
    supportActionBar?.show()
}

fun AppCompatActivity.hideToolbar() {
    supportActionBar?.hide()
}

fun AppCompatActivity.showKeyboard(view: View) {
    getInputMethodManager()?.showSoftInput(
        view,
        InputMethodManager.SHOW_IMPLICIT
    )
}

fun AppCompatActivity.hideKeyboard(view: View = window.decorView) {
    getInputMethodManager()?.hideSoftInputFromWindow(
        view.windowToken,
        InputMethodManager.HIDE_NOT_ALWAYS
    )
}

fun AppCompatActivity.hideKeyboardAlways(view: View = window.decorView) {
    getInputMethodManager()?.hideSoftInputFromWindow(view.windowToken, 0)
}

fun AppCompatActivity.toggleKeyboard(view: View = window.decorView) {
    getInputMethodManager()?.toggleSoftInputFromWindow(view.windowToken, 0, 0)
}

fun AppCompatActivity.getInputMethodManager(): InputMethodManager? {
    return getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
}

fun AppCompatActivity.getConnectivityManager(): ConnectivityManager? {
    return getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
}

fun AppCompatActivity.isGpsProviderEnabled(): Boolean {
    return getLocationManager()?.isProviderEnabled(LocationManager.GPS_PROVIDER) ?: false
}

fun AppCompatActivity.isNetworkProviderEnabled(): Boolean {
    return getLocationManager()?.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ?: false
}

fun AppCompatActivity.getLocationManager(): LocationManager? {
    return getSystemService(Context.LOCATION_SERVICE) as LocationManager?
}

fun AppCompatActivity.hasAutoFocus(): Boolean {
    return hasSystemFeature(PackageManager.FEATURE_CAMERA_AUTOFOCUS)
}

fun AppCompatActivity.hasFlash(): Boolean {
    return hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)
}

fun AppCompatActivity.hasSystemFeature(feature: String): Boolean {
    return packageManager.hasSystemFeature(feature)
}

fun AppCompatActivity.navigateToActivity(hasToFinish: Boolean, targetActivity: Class<*>) {
    startActivity(Intent(this, targetActivity))
    onFinish(hasToFinish)
}

fun AppCompatActivity.navigateToActivityAndClearTaskWithParams(
    hasToFinish: Boolean,
    targetActivity: Class<*>,
    extras: Bundle
) {
    startActivity(
        Intent(this, targetActivity).apply {
            putExtras(extras)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    )
    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    onFinish(hasToFinish)
}

fun AppCompatActivity.navigateToActivity(
    hasToFinish: Boolean,
    targetActivity: Class<*>,
    extras: Bundle,
    @AnimRes enterAnim: Int,
    @AnimRes exitAnim: Int
) {
    startActivity(Intent(this, targetActivity).apply { putExtras(extras) })
    onFinish(hasToFinish)
    overridePendingTransition(enterAnim, exitAnim)
}

fun AppCompatActivity.navigateToActivity(
    hasToFinish: Boolean,
    targetActivity: Class<*>,
    extras: Bundle,
    requestCode: Int
) {
    startActivityForResult(Intent(this, targetActivity).apply { putExtras(extras) }, requestCode)
    onFinish(hasToFinish)
}

fun AppCompatActivity.navigateToActivity(
    hasToFinish: Boolean,
    targetActivity: Class<*>,
    extras: Bundle,
    @AnimRes enterAnim: Int,
    @AnimRes exitAnim: Int,
    requestCode: Int
) {
    startActivityForResult(Intent(this, targetActivity).apply { putExtras(extras) }, requestCode)
    overridePendingTransition(enterAnim, exitAnim)
    onFinish(hasToFinish)
}

fun AppCompatActivity.navigateToActivityWithParams(
    hasToFinish: Boolean,
    targetActivity: Class<*>,
    extras: Bundle
) {
    navigateToActivity(
        hasToFinish,
        targetActivity,
        extras,
        R.anim.fade_in,
        R.anim.fade_out
    )
}

fun AppCompatActivity.navigateToActivity(
    hasToFinish: Boolean,
    targetActivity: Class<*>,
    flags: Int,
    @AnimRes enterAnim: Int,
    @AnimRes exitAnim: Int
) {
    startActivity(Intent(this, targetActivity).apply { addFlags(flags) })
    onFinish(hasToFinish)
    overridePendingTransition(enterAnim, exitAnim)
}

fun AppCompatActivity.navigateToActivity(
    hasToFinish: Boolean,
    clearStack: Boolean = false,
    targetActivity: Class<*>,
    @AnimRes enterAnim: Int,
    @AnimRes exitAnim: Int
) {
    takeIf { clearStack }?.run {
        navigateToActivity(
            hasToFinish,
            targetActivity,
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK,
            enterAnim,
            exitAnim
        )
    } ?: run {
        startActivity(Intent(this, targetActivity))
        onFinish(hasToFinish)
        overridePendingTransition(enterAnim, exitAnim)
    }
}

fun AppCompatActivity.navigateToActivity(
    hasToFinish: Boolean,
    targetActivity: Class<*>,
    flags: Int
) {
    navigateToActivity(
        hasToFinish,
        targetActivity,
        flags,
        R.anim.fade_in,
        R.anim.fade_out
    )
}

fun AppCompatActivity.navigateToActivityClearingTask(
    hasToFinish: Boolean,
    targetActivity: Class<*>
) {
    navigateToActivity(
        hasToFinish,
        targetActivity,
        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    )
}

fun AppCompatActivity.goToActivity(
    hasToFinish: Boolean,
    targetActivity: Class<*>,
    flags: Int,
    extras: Bundle,
    @AnimRes enterAnim: Int,
    @AnimRes exitAnim: Int
) {
    startActivity(
        Intent(this, targetActivity).apply {
            addFlags(flags)
            putExtras(extras)
        }
    )
    onFinish(hasToFinish)
    overridePendingTransition(enterAnim, exitAnim)
}

fun AppCompatActivity.goToActivityClearTaskWithParamsWithAnimation(
    hasToFinish: Boolean,
    targetActivity: Class<*>,
    extras: Bundle,
    @AnimRes enterAnim: Int,
    @AnimRes exitAnim: Int
) {
    startActivity(
        Intent(this, targetActivity).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            putExtras(extras)
        }
    )
    onFinish(hasToFinish)
    overridePendingTransition(enterAnim, exitAnim)
}

fun AppCompatActivity.goToActivityWithFlagsWithParams(
    hasToFinish: Boolean,
    targetActivity: Class<*>,
    flags: Int,
    extras: Bundle
) {
    startActivity(
        Intent(this, targetActivity).apply {
            addFlags(flags)
            putExtras(extras)
        }
    )
    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    onFinish(hasToFinish)
}

fun AppCompatActivity.goToSettings() {
    startActivity(
        Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", packageName, null)
        ).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    )
}

fun AppCompatActivity.goToExternalBrowser(url: String, @ColorRes toolbarColor: Int) {
    val context = this

    val builder = CustomTabsIntent.Builder().apply {
        setToolbarColor(ContextCompat.getColor(context, toolbarColor))
        setShowTitle(true)
        enableUrlBarHiding()
        setStartAnimations(context, R.anim.slide_in_right, R.anim.slide_out_left)
        setExitAnimations(context, R.anim.slide_in_left, R.anim.slide_out_right)
    }

    openUrl(context, builder, url)
}

private fun AppCompatActivity.onFinish(hasToFinish: Boolean) {
    if (hasToFinish) {
        finishAfterTransition()
    }
}

fun AppCompatActivity.callTo(phoneNumber: String, requestCode: Int) {
    val intent = Intent(Intent.ACTION_CALL)

    intent.data = Uri.parse("tel:$phoneNumber")
    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
        val permissions = arrayOfNulls<String>(1)
        permissions[0] = Manifest.permission.CALL_PHONE
        requestPermissions(permissions, requestCode)
    } else {
        startActivity(intent)
    }
}
