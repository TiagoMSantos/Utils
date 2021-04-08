package com.tiagomdosantos.utils.lib.permissions

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.tiagomdosantos.utils.lib.views.activities.PermissionsActivity

class EntityPermission(private val activity: Activity) {

    /**
     * This method start the activity for request permission
     * @param requestCode code of request
     * @param permissions permissions
     */
    internal fun startActivityPermissions(requestCode: Int, permissions: Array<String>) {
        val bundle = Bundle().apply {
            putInt("request_code", requestCode)
            putStringArray("permissions", permissions)
        }

        val intent = Intent(activity, PermissionsActivity::class.java).apply {
            putExtra("extras", bundle)
        }

        activity.startActivity(intent)
    }

    /**
     * This method to check if you have this permission
     * @param permission permission
     * @return true if the app has permitted or false if no
     */
    internal fun checkPermissionGranted(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * This method to check if you don't have this permission
     * @param permission permission
     * @return true if the app doesn't has permitted or false if no
     */
    internal fun checkPermissionDenied(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_DENIED
    }

    /**
     * This method returns true if the app has requested this permission
     * previously and the user denied the request.
     * @param permission permissions
     * @return false if the app has denied permanently
     */
    internal fun checkRationalePermission(permission: String): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)
    }

    /**
     * This method to show dialog error with permissions permanently denied
     * her action open the app settings.
     * @param permissions list permission permanently denied
     */
    internal fun showDialogPermissionError(permissions: Array<String>) {
        showDialogPermissionError(permissions, null)
    }

    internal fun showDialogPermissionError(permissions: Array<String>, permissionsDeniedMessage: String? = null) {
        val message = permissionsDeniedMessage ?: getPermissionErrorMessage(permissions)

        // TODO: Mostrar dialog
//        (activity as AppCompatActivity).showPermissionDialogFragment(message)
    }

    private fun getPermissionErrorMessage(permissions: Array<String>): String {
        // TODO: Ajustar
        val string = "Para continuar precisamos das seguintes permissões:\\n\\n%s\\n\\nVerifique suas configurações e tente novamente"

        return getPermissionNameErrorMessage(permissions).takeIf { it.isNotBlank() }
            ?.run { String.format(string, substring(0, length - 2)) }
            ?: string
    }

    private fun getPermissionNameErrorMessage(permissions: Array<String>): StringBuilder {
        val newPermissionName = StringBuilder()

        for (permission in permissions) {
            if (checkPermissionGranted(permission)) {
                continue
            }

            try {
                newPermissionName.run {
                    getPermissionName(permission).takeIf { !contains(it, true) }
                        ?.let { append("$it, ") }
                }
            } catch (e: PackageManager.NameNotFoundException) {
                FirebaseCrashlytics.getInstance().recordException(e)
            }
        }

        return newPermissionName
    }

    /**
     * This method retrieve the permission name based in bundle permission
     * @param permission bundle
     * @return name permission
     * @throws PackageManager.NameNotFoundException exception
     */
    @Throws(PackageManager.NameNotFoundException::class)
    private fun getPermissionName(permission: String): String {
        val packageManager = activity.packageManager
        val permissionInfo = packageManager.getPermissionInfo(permission, 0)
        val permissionGroupInfo = packageManager.getPermissionGroupInfo(permissionInfo?.group.orEmpty(), 0)

        return permissionGroupInfo.loadLabel(packageManager).toString()
    }
}
