package com.oggtechnologies.salvos.utilities

class Square(val pos: Vector, val size: Float) {
    val top: Float
        get() = pos.y
    val left: Float
        get() = pos.x
    val bottom: Float
        get() = pos.y + size
    val right: Float
        get() = pos.x + size
}