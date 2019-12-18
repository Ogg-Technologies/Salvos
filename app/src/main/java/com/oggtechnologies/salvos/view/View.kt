package com.oggtechnologies.salvos.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.SurfaceView
import com.oggtechnologies.salvos.model.ModelViewer

class View(context: Context, val model: ModelViewer) : SurfaceView(context), Representer {

    private fun drawScreen(canvas: Canvas) {
        canvas.drawColor(-0x010101)
        val paint = Paint()
        paint.color = -0x551122
        val x = model.x
        canvas.drawCircle(x.toFloat(), 300F, 70F, paint)
    }

    override fun represent() {
        if (holder.surface.isValid) {
            val canvas = holder.lockCanvas()
            drawScreen(canvas)
            holder.unlockCanvasAndPost(canvas)
        }
    }

    fun hideAndroidUI() {
        systemUiVisibility =
            android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
    }
}