package com.tiagomdosantos.utils.lib.databinding

import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

object ImageViewBindingAdapter {

    @BindingAdapter("srcCompat")
    fun ImageView.setDrawable(drawable: Drawable? = null) {
        setImageDrawable(drawable)
    }

    @BindingAdapter("srcCompat")
    fun ImageView.setDrawableRes(@DrawableRes drawable: Int? = null) {
        setImageResource(drawable ?: 0)
    }

    @BindingAdapter("srcCompat")
    fun ImageView.setBitmap(bitmap: Bitmap? = null) {
        setImageBitmap(bitmap)
    }

    @BindingAdapter("tintRes")
    fun ImageView.setTintRes(@ColorRes color: Int) {
        setTint(ContextCompat.getColor(context, color))
    }

    @BindingAdapter("tintInt")
    fun ImageView.setTintInt(@ColorInt color: Int) {
        setTint(color)
    }

    @BindingAdapter("srcCompat")
    fun ImageView.setSrcCompatDrawable(@Nullable drawable: Drawable) {
        setDrawable(drawable)
    }

    private fun ImageView.setTint(
        @ColorInt color: Int,
        mode: PorterDuff.Mode = PorterDuff.Mode.SRC_ATOP
    ) {
        setColorFilter(color, mode)
    }

    private fun ImageView.load(url: String?, options: RequestOptions) {
        Glide.with(context)
            .load(url)
            .apply(options)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }

    private fun ImageView.load(imageByteArray: ByteArray?, options: RequestOptions) {
        Glide.with(context)
            .load(imageByteArray)
            .apply(options)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}
