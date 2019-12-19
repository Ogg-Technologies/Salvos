package com.oggtechnologies.salvos.view.drawers.tiledrawers

import android.graphics.Canvas
import com.oggtechnologies.salvos.utilities.Square

interface TileDrawer {
    fun draw(square: Square, canvas: Canvas)
}