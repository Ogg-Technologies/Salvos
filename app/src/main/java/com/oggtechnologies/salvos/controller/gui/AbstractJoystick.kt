package com.oggtechnologies.salvos.controller.gui

import com.oggtechnologies.salvos.utilities.Vector

abstract class AbstractJoystick(override val pos: Vector, val radius: Float) : GUIElement {
    private var lastTouchPos: Vector = pos
    private var touchedFingerID: Int? = null

    private val dir: Vector
        get() = lastTouchPos - pos

    override fun touchDown(screenPos: Vector, fingerID: Int) {
        if (touchIsOnJoystick(screenPos)) {
            touchedFingerID = fingerID
            lastTouchPos = screenPos
            onDirChanged(dir)
        }
    }

    override fun touchRelease(screenPos: Vector, fingerID: Int) {
        if (fingerID == touchedFingerID) {
            touchedFingerID = null
            lastTouchPos = pos
            onDirChanged(dir)
        }
    }

    override fun touchMove(screenPos: Vector, fingerID: Int) {
        if (fingerID == touchedFingerID) {
            lastTouchPos = screenPos
            onDirChanged(dir)
        }
    }

    abstract fun onDirChanged(dir: Vector)

    private fun touchIsOnJoystick(touchPos: Vector): Boolean {
        return (pos-touchPos).mag <= radius
    }
}