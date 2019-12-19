package com.oggtechnologies.salvos.view.drawers.tiledrawers

import android.graphics.Canvas
import com.oggtechnologies.salvos.utilities.Square
import com.oggtechnologies.salvos.utilities.SharedPaint

class WallDrawer : TileDrawer {
    override fun draw(square: Square, canvas: Canvas) {
        SharedPaint.color = -0x33aaff
        canvas.drawRect(square.left, square.top, square.right, square.bottom,
            SharedPaint
        )
    }

}