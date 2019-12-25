package com.oggtechnologies.salvos.utilities.shapes.implementations

import com.oggtechnologies.salvos.utilities.Vector
import com.oggtechnologies.salvos.utilities.shapes.interfaces.Box
import com.oggtechnologies.salvos.utilities.shapes.interfaces.Shape

class Square(override val pos: Vector, val size: Float) : Shape,
    Box {
    override val top: Float
        get() = pos.y
    override val left: Float
        get() = pos.x
    override val bottom: Float
        get() = pos.y + size
    override val right: Float
        get() = pos.x + size
    override val center: Vector
        get() = pos + Vector(
            size / 2,
            size / 2
        )
    override val centerX: Float
        get() = pos.x + size / 2
    override val centerY: Float
        get() = pos.y + size / 2
    override val width: Float
        get() = size
    override val height: Float
        get() = size

    companion object {
        fun byCenter(center: Vector, size: Float) : Square {
            return Square(
                center - Vector(
                    size / 2,
                    size / 2
                ), size
            )
        }
    }
}