package com.oggtechnologies.salvos.utilities

class Vector(val x: Float, val y: Float) {
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

    override fun toString(): String {
        return "Vector(x=$x, y=$y)"
    }

    constructor() : this(0F, 0F)


}