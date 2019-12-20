package com.oggtechnologies.salvos.view.drawers.tiledrawers

import android.graphics.Canvas
import android.graphics.Color
import com.oggtechnologies.salvos.utilities.Square
import com.oggtechnologies.salvos.utilities.SharedPaint

class WallDrawer : TileDrawer {
    override fun draw(square: Square, canvas: Canvas) {
        SharedPaint.color = Color.GRAY
        val left = square.left
        val top = square.top
        val right = square.right
        val bottom = square.bottom
        canvas.drawRect(left, top, right, bottom, SharedPaint)
        SharedPaint.color = Color.BLUE
        canvas.drawLine(left, top, right, bottom, SharedPaint)
        canvas.drawLine(left, bottom, right, top, SharedPaint)
    }

}