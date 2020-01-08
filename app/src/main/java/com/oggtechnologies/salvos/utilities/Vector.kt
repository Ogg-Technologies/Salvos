package com.oggtechnologies.salvos.utilities

import kotlin.math.hypot

data class Vector(val x: Float, val y: Float) {
    operator fun plus(v: Vector): Vector {
        return Vector(x + v.x, y + v.y)
    }

    operator fun minus(v: Vector): Vector {
        return Vector(x-v.x, y-v.y)
    }

    operator fun times(amount: Float): Vector {
        return Vector(x*amount, y*amount)
    }

    operator fun div(amount: Float): Vector {
        return Vector(x/amount, y/amount)
    }

    constructor() : this(0F, 0F)

    val mag: Float
        get() = hypot(x.toDouble(), y.toDouble()).toFloat()

    fun clampMag(newMag: Float): Vector {
        return if (newMag<mag) {
            this*(newMag/mag)
        } else {
            this
        }
    }

    fun setX(newX: Float): Vector {
        return Vector(newX, y)
    }

    fun setY(newY: Float): Vector {
        return Vector(x, newY)
    }
}