package com.oggtechnologies.salvos.controller.gui

import android.graphics.Canvas
import android.graphics.Color
import com.oggtechnologies.salvos.utilities.SharedPaint
import com.oggtechnologies.salvos.utilities.Vector
import com.oggtechnologies.salvos.utilities.collidingWith
import com.oggtechnologies.salvos.utilities.shapes.implementations.Circle
import com.oggtechnologies.salvos.utilities.shapes.implementations.Point

abstract class AbstractJoystick(pos: Vector, radius: Float) : GUIElement {
    private val bounds = Circle(pos, radius)
    private var lastTouchPos: Vector = pos
    private var touchedFingerID: Int? = null

    private val dir: Vector
        get() = (lastTouchPos - bounds.pos).clampMag(bounds.radius)

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
            lastTouchPos = bounds.pos
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
        return bounds collidingWith Point(touchPos)
    }

    override fun draw(canvas: Canvas) {
        SharedPaint.color = Color.GRAY
        SharedPaint.alpha = 200
        canvas.drawCircle(bounds.pos.x, bounds.pos.y, bounds.radius, SharedPaint)
        val ead: Vector = bounds.pos+dir
        canvas.drawCircle(ead.x, ead.y, bounds.radius/2, SharedPaint)
        SharedPaint.alpha = 255
    }
}