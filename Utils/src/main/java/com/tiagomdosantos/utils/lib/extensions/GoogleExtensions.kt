package com.tiagomdosantos.utils.lib.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsService
import com.google.android.gms.common.GoogleApiAvailability

fun Context.isGooglePlayServicesAvailable(version: Int = GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE): Int {
    return GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this, version)
}

fun AppCompatActivity.isGooglePlayServicesAvailable(version: Int = GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE): Int {
    return GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this, version)
}

fun openUrl(context: Context, builder: CustomTabsIntent.Builder, url: String) {
    Uri.parse(url).let {
        context.run {
            takeIf { isChromeTabSupported(it) }
                ?.run { builder.build().launchUrl(this, it) }
                ?: startActivity(Intent(Intent.ACTION_VIEW, it))
        }
    }
}

private fun isChromeTabSupported(context: Context): Boolean {
    val packageManager = context.packageManager
    val resolvedActivities = packageManager.queryIntentActivities(Intent(Intent.ACTION_VIEW, Uri.parse("http://www.test-url.com")), 0)

    for (info in resolvedActivities) {
        Intent().apply {
            action = CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION
            setPackage(info.activityInfo.packageName)
        }.run { packageManager.resolveService(this, 0)?.run { return true } }
    }

    return false
}
