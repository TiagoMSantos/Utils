package com.tiagomdosantos.utils.lib.builders

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.view.View

object BlurBuilder {

    private const val BITMAP_SCALE = 0.6f
    private const val BLUR_RADIUS = 15f

    /**
     * Retrieve bitmap from view.
     * @param view view
     * @return bitmap
     */
    @Throws(Exception::class)
    private fun getBitmapFromView(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val c = Canvas(bitmap)
        view.layout(view.left, view.top, view.right, view.bottom)
        view.draw(c)
        return bitmap
    }

    /**
     * Apply blur efect on bitmap.
     * @param context context
     * @param view view
     * @return bitmap blured ;)
     */
    @Throws(Exception::class)
    fun applyBlur(context: Context, view: View): Bitmap {

        val bitmap = getBitmapFromView(view)

        val width = Math.round(bitmap.width * BITMAP_SCALE)
        val height = Math.round(bitmap.height * BITMAP_SCALE)

        val inputBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false)
        val outputBitmap = Bitmap.createBitmap(inputBitmap)

        val rs = RenderScript.create(context)

        val intrinsicBlur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
        val tmpIn = Allocation.createFromBitmap(rs, inputBitmap)
        val tmpOut = Allocation.createFromBitmap(rs, outputBitmap)

        intrinsicBlur.setRadius(BLUR_RADIUS)
        intrinsicBlur.setInput(tmpIn)
        intrinsicBlur.forEach(tmpOut)
        tmpOut.copyTo(outputBitmap)

        return outputBitmap
    }
}
