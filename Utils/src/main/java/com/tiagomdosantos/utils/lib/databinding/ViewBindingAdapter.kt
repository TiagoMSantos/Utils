package com.tiagomdosantos.utils.lib.databinding

import android.animation.Animator
import android.graphics.drawable.BitmapDrawable
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.tiagomdosantos.utils.lib.builders.BlurBuilder
import com.tiagomdosantos.utils.lib.extensions.convertDpToPixel
import kotlin.math.hypot

object ViewBindingAdapter {
    @JvmStatic
    @BindingAdapter("errorEnabled")
    fun TextInputLayout.setErrorEnabled(errorEnabled: Boolean?) {
        isErrorEnabled = errorEnabled ?: false
    }

    @JvmStatic
    @BindingAdapter("error")
    fun TextInputLayout.setTextInputLayoutError(errorString: String) {
        error = errorString
    }

    @JvmStatic
    @BindingAdapter("blurEnabled")
    fun View.addBlur(isEnabled: Boolean) {
        background = takeIf { isEnabled }?.run {
            try {
                val bitmap = BlurBuilder.applyBlur(context, parent as View)
                BitmapDrawable(context.resources, bitmap)
            } catch (e: Exception) {
                null
            }
        } ?: run {
            null
        }
    }

    @JvmStatic
    @BindingAdapter(
        "enterCircularDuration",
        "enterCircularDelay",
        "enterCircularShow",
        "enterCircularPadding"
    )
    fun addEnterCircularTransition(
        view: View,
        duration: Long,
        delay: Long,
        show: Boolean,
        padding: Int
    ) {
        Handler(Looper.getMainLooper()).postDelayed(
            {
                val radius = hypot(view.width.toDouble(), view.height.toDouble()).toInt()
                val x = view.right - padding.toFloat().convertDpToPixel()
                val y = view.top + padding.toFloat().convertDpToPixel()

                val animator = ViewAnimationUtils.createCircularReveal(
                    view,
                    x,
                    y,
                    (if (show) 0 else radius).toFloat(),
                    (if (show) radius else 0).toFloat()
                )

                animator.interpolator = AccelerateDecelerateInterpolator()
                animator.duration = duration

                animator.addListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator) {
                        view.visibility = View.VISIBLE
                    }

                    override fun onAnimationEnd(animation: Animator) {
                        if (!show)
                            view.visibility = View.INVISIBLE
                    }

                    override fun onAnimationCancel(animation: Animator) {}

                    override fun onAnimationRepeat(animation: Animator) {}
                })

                animator.start()
            },
            delay
        )
    }
}
