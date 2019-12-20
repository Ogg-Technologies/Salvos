package com.oggtechnologies.salvos.view.drawers.entitydrawers

import android.graphics.Canvas
import android.graphics.Color
import com.oggtechnologies.salvos.utilities.SharedPaint
import com.oggtechnologies.salvos.utilities.Square

class PlayerDrawer {
    fun draw(square: Square, canvas: Canvas) {
        SharedPaint.color = Color.GREEN
        canvas.drawCircle(square.centerX, square.centerY, square.size/2, SharedPaint)
    }
}