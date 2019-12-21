package com.oggtechnologies.salvos.controller.joystick

import com.oggtechnologies.salvos.utilities.Vector

class DefaultJoystick(private val pos: Vector, private val radius: Float): Joystick {

    private var lastTouchPos: Vector? = null
    private var touchedFingerID: Int? = null

    override val isTouched: Boolean
        get() {
            return lastTouchPos != null
        }

    override val movedDirection: Vector
        get() {
            return if (isTouched == null) {
                Vector(0F, 0F)
            } else {
                pos - lastTouchPos!!
            }
        }

    override fun touchDown(screenPos: Vector, fingerID: Int) {
        if (touchIsOnJoystick(screenPos)) {
            touchedFingerID = fingerID
            lastTouchPos = screenPos
        }
    }

    override fun touchRelease(screenPos: Vector, fingerID: Int) {
        if (fingerID == touchedFingerID) {

        }
    }

    override fun touchMove(screenPos: Vector, fingerID: Int) {
    }

    private fun touchIsOnJoystick(touchPos: Vector): Boolean {
        return (pos-touchPos).mag <= radius
    }
}

