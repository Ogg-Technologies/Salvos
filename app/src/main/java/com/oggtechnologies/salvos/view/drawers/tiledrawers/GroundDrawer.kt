package com.oggtechnologies.salvos.view.drawers.tiledrawers

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.oggtechnologies.salvos.utilities.Square
import com.oggtechnologies.salvos.utilities.SharedPaint

class GroundDrawer : TileDrawer {
    override fun draw(square: Square, canvas: Canvas) {
        SharedPaint.color = Color.BLACK
        canvas.drawRect(square.left, square.top, square.right, square.bottom, SharedPaint)
    }

}