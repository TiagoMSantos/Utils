package com.tiagomdosantos.utils.lib

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.IntDef
import androidx.appcompat.app.AppCompatActivity

class PermissionsManager(private val activity: AppCompatActivity) {

    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    @IntDef(GRANTED, DENIED, PERMANENTLY_DENIED) annotation class Status
    companion object {
        private var listener: OnRequestPermissionsResult? = null
        private var statusListener: OnRequestPermissionsStatus? = null

        const val GRANTED = 0
        const val DENIED = 1
        const val PERMANENTLY_DENIED = 2
    }

    interface OnRequestPermissionsResult {
        fun onRequestPermissionsResult(result: Boolean)
    }

    interface OnRequestPermissionsStatus {
        fun onRequestPermissionsStatus(@Status status: Int)
    }

    private var entityPermission: EntityPermission? = EntityPermission(activity)

    private val permissionSharedPreferences: SharedPreferences = activity.getPreferences(Context.MODE_PRIVATE)

    /**
     * Set listener for retrieve status of request
     *
     * @param onRequestPermissionsStatus status of request
     * @return a instance of PermissionsManager
     */
    fun setOnPermissionStatusListener(onRequestPermissionsStatus: OnRequestPermissionsStatus) {
        statusListener = onRequestPermissionsStatus
    }

    /**
     * Request multiples permissions
     *
     * @param permissions permissions
     */
    fun requestPermission(requestCode: Int, permissions: Array<String>) {
        when {
            hasPermissionsGranted(*permissions) -> {
                listener?.onRequestPermissionsResult(true)
                statusListener?.onRequestPermissionsStatus(GRANTED)
            }
            hasPermissionPermanentlyDenied(*permissions) -> {
                listener?.onRequestPermissionsResult(false)
                statusListener?.onRequestPermissionsStatus(PERMANENTLY_DENIED)

                destroy()
            }
            else -> entityPermission?.startActivityPermissions(requestCode, permissions)
        }
    }

    fun getPermissionsGranted(vararg permissions: String): List<String> {
        return permissions.filter { entityPermission?.checkPermissionGranted(it) == true }
    }

    fun hasPermissions(vararg permissions: String): Boolean {
        return hasPermissionsGranted(*permissions)
    }

    fun hasPermissionsGranted(vararg permissions: String): Boolean {
        return permissions.all { entityPermission?.checkPermissionGranted(it) == true }
    }

    fun getPermissionsDenied(vararg permissions: String): List<String> {
        return permissions.filter { entityPermission?.checkPermissionDenied(it) == true }
    }

    fun hasPermissionDenied(vararg permissions: String): Boolean {
        return permissions.any { entityPermission?.checkPermissionDenied(it) == true }
    }

    fun hasPermissionPermanentlyDenied(vararg permissions: String): Boolean {
        for (permission in getPermissionsDenied(*permissions)) {
            entityPermission?.takeIf { !it.checkRationalePermission(permission) }
                ?.run {
                    takeIf { permissionSharedPreferences.getBoolean(permission, true) }
                        ?.run {
                            permissionSharedPreferences.edit().putBoolean(permission, false).apply()
                        }
                        ?: return true
                }
        }

        return false
    }

    /**
     * Retrieve the results of requested this permissions
     *
     * @param permissions permissions
     */
    fun onPermissionsRequested(permissions: Array<String>) {
        takeIf { hasPermissionsGranted(*permissions) }
            ?.apply {
                listener?.onRequestPermissionsResult(true)
                statusListener?.onRequestPermissionsStatus(GRANTED)
            }
            ?: let {
                for (permission in getPermissionsDenied(*permissions)) {
                    entityPermission?.takeIf { !it.checkRationalePermission(permission) }
                        ?.run {
                            takeIf { permissionSharedPreferences.getBoolean(permission, true) }
                                ?.run {
                                    permissionSharedPreferences.edit().putBoolean(permission, false).apply()
                                }
                                ?: apply {
                                    listener?.onRequestPermissionsResult(false)
                                    statusListener?.onRequestPermissionsStatus(PERMANENTLY_DENIED)

                                    return@let
                                }
                        }
                }

                listener?.onRequestPermissionsResult(false)
                statusListener?.onRequestPermissionsStatus(DENIED)
            }

        destroy()
    }

    private fun destroy() {
        listener = null
        statusListener = null
        entityPermission = null
    }
}
