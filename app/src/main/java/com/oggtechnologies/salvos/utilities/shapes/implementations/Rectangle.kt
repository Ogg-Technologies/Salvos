package com.oggtechnologies.salvos.utilities.shapes.implementations

import com.oggtechnologies.salvos.utilities.Vector
import com.oggtechnologies.salvos.utilities.shapes.interfaces.Box
import com.oggtechnologies.salvos.utilities.shapes.interfaces.Shape

class Rectangle(override val pos: Vector, val size: Vector) : Shape,
    Box {
    override val top: Float
        get() = pos.y
    override val left: Float
        get() = pos.x
    override val bottom: Float
        get() = pos.y + size.y
    override val right: Float
        get() = pos.x + size.x
    override val center: Vector
        get() = pos + size/2F
    override val centerX: Float
        get() = pos.x + size.x / 2
    override val centerY: Float
        get() = pos.y + size.y / 2
    override val width: Float
        get() = size.x
    override val height: Float
        get() = size.y
}