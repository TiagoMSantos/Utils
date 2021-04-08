package com.tiagomdosantos.utils.lib.extensions

import android.content.Context
import android.net.wifi.SupplicantState
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import java.math.BigInteger
import java.net.InetAddress
import java.net.NetworkInterface
import java.util.Collections

fun Context.wifiIP(): String {
    val ipAddress = getWifiManager().connectionInfo.ipAddress
    val ipByteArray = BigInteger.valueOf(ipAddress.toLong()).toByteArray()
    return InetAddress.getByAddress(ipByteArray).hostAddress
}

fun mobileIP(): String {
    var ip = ""
    val interfaces = Collections.list(NetworkInterface.getNetworkInterfaces())
    interfaces.forEach { item ->
        val addresses = Collections.list(item.inetAddresses)
        addresses.forEach { address ->
            if (!address.isLoopbackAddress)
                ip = address.hostAddress
        }
    }
    return ip
}

fun Context.isWifiConnection(): Boolean {
    return getWifiManager().connectionInfo.supplicantState == SupplicantState.COMPLETED
}

fun Context.currentIP() = if (isWifiConnection()) wifiIP() else mobileIP()

private fun Context.getWifiManager(): WifiManager {
    return getSystemService(AppCompatActivity.WIFI_SERVICE) as WifiManager
}
