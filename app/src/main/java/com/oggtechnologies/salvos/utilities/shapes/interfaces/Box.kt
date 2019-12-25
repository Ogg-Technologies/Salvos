package com.oggtechnologies.salvos.utilities.shapes.interfaces

import com.oggtechnologies.salvos.utilities.Vector

interface Box {
    val pos: Vector
    val top: Float
    val left: Float
    val bottom: Float
    val right: Float
    val center: Vector
    val centerX: Float
    val centerY: Float
    val width: Float
    val height: Float
}
