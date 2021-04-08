package com.tiagomdosantos.utils.lib.providers

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.IntegerRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.tiagomdosantos.utils.lib.extensions.getDrawableWithContext
import com.tiagomdosantos.utils.lib.extensions.setColor

class ResourceProvider constructor(private val context: Context) {
    companion object {
        private const val DRAWABLE_RESOURCE_NAME = "drawable"
    }

    fun getColor(@ColorRes resourceId: Int): Int {
        return ContextCompat.getColor(context, resourceId)
    }

    fun getDrawable(@DrawableRes resourceId: Int): Drawable? {
        return context.getDrawableWithContext(resourceId)
    }

    fun getDrawableWithColor(
        @DrawableRes iconResourceId: Int,
        @ColorRes colorResourceId: Int
    ): Drawable? {
        return getDrawable(iconResourceId).apply {
            setColor(getColor(colorResourceId))
        }
    }

    fun getInteger(@IntegerRes resourceId: Int): Int {
        return context.resources.getInteger(resourceId)
    }

    fun getString(
        @StringRes resourceId: Int,
        vararg formatArgs: Any?
    ): String {
        return context.getString(resourceId, *formatArgs)
    }

    fun getQuantityString(
        @PluralsRes resourceId: Int,
        quantity: Int,
        vararg formatArgs: Any?
    ): String {
        return context.resources.getQuantityString(resourceId, quantity, *formatArgs)
    }

    fun getDrawableIdentifier(drawableName: String): Int {
        return context.resources.getIdentifier(
            drawableName,
            DRAWABLE_RESOURCE_NAME,
            context.packageName
        )
    }
}
