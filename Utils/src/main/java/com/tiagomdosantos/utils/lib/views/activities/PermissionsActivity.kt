package com.tiagomdosantos.utils.lib.views.activities

import android.app.Activity
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.tiagomdosantos.utils.lib.EntityPermission
import com.tiagomdosantos.utils.lib.PermissionsManager

class PermissionsActivity : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

        intent?.getBundleExtra("extras")
            ?.let {
                val permissions = it.getStringArray("permissions")
                val requestCode = it.getInt("request_code")

                permissions?.takeIf { permissions.isNotEmpty() }
                    ?.run { requestPermissions(this@PermissionsActivity, requestCode, this) }
            }
            ?: finish()
    }

    /**
     * Retrieve the results of requested this permissions
     *
     * @param permissions permissions
     */
    private fun requestPermissions(activity: Activity, requestCode: Int, permissions: Array<String>) {
        val listPermissionsNeeded = ArrayList<String>()

        for (permission in permissions) {
            if (EntityPermission(activity).checkPermissionDenied(permission)) {
                listPermissionsNeeded.add(permission)
            }
        }

        if (listPermissionsNeeded.isEmpty()) {
            return
        }

        ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toTypedArray(), requestCode)
    }

    /**
     * Retrieve the results of requested this permissions
     *
     * @param permissions permissions
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        PermissionsManager(this).onPermissionsRequested(permissions)

        finish()
    }
}
