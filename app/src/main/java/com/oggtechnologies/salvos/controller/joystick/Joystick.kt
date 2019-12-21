package com.oggtechnologies.salvos.controller.joystick

import com.oggtechnologies.salvos.utilities.Vector

interface Joystick {
    val isTouched: Boolean

    val movedDirection: Vector

    fun touchDown(screenPos: Vector, fingerID: Int)

    fun touchRelease(screenPos: Vector, fingerID: Int)

    fun touchMove(screenPos: Vector, fingerID: Int)
}