package com.tiagomdosantos.utils.lib.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tiagomdosantos.utils.lib.R
import com.tiagomdosantos.utils.lib.extensions.navigateToActivity

class BaseRouter(private val activity: AppCompatActivity) {
    fun onBackPressed() {
        activity.run {
            onBackPressed()
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }
    }

    fun back() {
        activity.run {
            finish()
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }
    }

    fun close() {
        activity.run {
            finish()
            overridePendingTransition(0, R.anim.slide_out_down)
        }
    }

    fun closeFlow() {
        activity.run {
            finishAffinity()
            overridePendingTransition(0, R.anim.slide_out_down)
        }
    }

    fun navigateToRight(
        targetActivity: Class<*>,
        bundle: Bundle = Bundle(),
        hasToFinish: Boolean = false,
        requestCode: Int? = null
    ) {
        requestCode?.let {
            activity.navigateToActivity(
                hasToFinish = hasToFinish,
                targetActivity = targetActivity,
                extras = bundle,
                enterAnim = R.anim.slide_in_right,
                exitAnim = R.anim.slide_out_left,
                requestCode = it
            )
        } ?: run {
            activity.navigateToActivity(
                hasToFinish = hasToFinish,
                targetActivity = targetActivity,
                extras = bundle,
                enterAnim = R.anim.slide_in_right,
                exitAnim = R.anim.slide_out_left
            )
        }
    }

    fun showFromBottom(
        targetActivity: Class<*>,
        bundle: Bundle = Bundle(),
        hasToFinish: Boolean = false,
        requestCode: Int? = null
    ) {
        requestCode?.let {
            activity.navigateToActivity(
                hasToFinish = hasToFinish,
                targetActivity = targetActivity,
                extras = bundle,
                enterAnim = R.anim.slide_in_up,
                exitAnim = R.anim.fade_out,
                requestCode = it
            )
        } ?: run {
            activity.navigateToActivity(
                hasToFinish = hasToFinish,
                targetActivity = targetActivity,
                extras = bundle,
                enterAnim = R.anim.slide_in_up,
                exitAnim = R.anim.fade_out
            )
        }
    }

    fun setResult(result: Int) {
        activity.setResult(result)
    }
}
