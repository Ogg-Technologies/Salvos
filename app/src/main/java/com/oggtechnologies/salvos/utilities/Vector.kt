package com.oggtechnologies.salvos.utilities

import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.sin

data class Vector(val x: Float, val y: Float) {

    val angle: Float
        get() = atan2(y, x)

    val mag: Float
        get() = hypot(x, y)

    operator fun plus(v: Vector): Vector {
        return Vector(x + v.x, y + v.y)
    }

    operator fun minus(v: Vector): Vector {
        return Vector(x - v.x, y - v.y)
    }

    operator fun times(amount: Float): Vector {
        return Vector(x * amount, y * amount)
    }

    operator fun div(amount: Float): Vector {
        return Vector(x / amount, y / amount)
    }

    constructor() : this(0F, 0F)

    fun clampMag(newMag: Float): Vector {
        return if (newMag < mag) {
            this * (newMag / mag)
        } else {
            this
        }
    }

    fun setAngle(newAngle: Float): Vector {
        return Vector(mag * cos(newAngle), mag * sin(newAngle))
    }

    fun setX(newX: Float): Vector {
        return Vector(newX, y)
    }

    fun setY(newY: Float): Vector {
        return Vector(x, newY)
    }
}