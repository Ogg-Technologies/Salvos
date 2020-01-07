package com.oggtechnologies.salvos.view.drawers.entitydrawers

import android.graphics.Canvas
import android.graphics.Color
import com.oggtechnologies.salvos.utilities.SharedPaint
import com.oggtechnologies.salvos.utilities.shapes.implementations.Circle
import com.oggtechnologies.salvos.utilities.shapes.implementations.Square

class PlayerDrawer {
    fun draw(square: Square, canvas: Canvas) {
        SharedPaint.color = Color.GREEN
        canvas.drawRect(square.left, square.top, square.right, square.bottom, SharedPaint)
    }
}