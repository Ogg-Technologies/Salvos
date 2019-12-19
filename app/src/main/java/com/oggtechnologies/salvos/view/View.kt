package com.oggtechnologies.salvos.view

import android.graphics.Canvas
import android.graphics.Paint
import com.oggtechnologies.salvos.model.ModelViewer

class View(private val model: ModelViewer) {
    fun draw(canvas: Canvas) {
        canvas.drawColor(-0x010101)
        val paint = Paint()
        paint.color = -0x551122
        val x = model.x
        canvas.drawCircle(x.toFloat(), 300F, 70F, paint)
    }

}