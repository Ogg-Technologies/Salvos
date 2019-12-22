package com.oggtechnologies.salvos.utilities.shapes

import com.oggtechnologies.salvos.utilities.Vector

class Square(val pos: Vector, val size: Float) {
    val top: Float
        get() = pos.y
    val left: Float
        get() = pos.x
    val bottom: Float
        get() = pos.y + size
    val right: Float
        get() = pos.x + size
    val center: Vector
        get() = pos + Vector(
            size / 2,
            size / 2
        );
    val centerX: Float
        get() = pos.x + size / 2
    val centerY: Float
        get() = pos.y + size / 2

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