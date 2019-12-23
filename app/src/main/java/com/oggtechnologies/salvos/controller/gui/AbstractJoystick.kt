package com.oggtechnologies.salvos.controller.gui

import android.graphics.Canvas
import android.graphics.Color
import com.oggtechnologies.salvos.utilities.SharedPaint
import com.oggtechnologies.salvos.utilities.Vector

abstract class AbstractJoystick(val pos: Vector, val radius: Float) : GUIElement {
    private var lastTouchPos: Vector = pos
    private var touchedFingerID: Int? = null

    private val dir: Vector
        get() = (lastTouchPos - pos).clampMag(radius)

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

    override fun draw(canvas: Canvas) {
        SharedPaint.color = Color.GRAY
        SharedPaint.alpha = 200
        canvas.drawCircle(pos.x, pos.y, radius, SharedPaint)
        val ead: Vector = pos+dir
        canvas.drawCircle(ead.x, ead.y, radius/2, SharedPaint)
        SharedPaint.alpha = 255
    }
}