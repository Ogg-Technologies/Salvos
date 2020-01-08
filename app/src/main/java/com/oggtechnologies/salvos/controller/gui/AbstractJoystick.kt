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

    private val headDeltaPos: Vector
        get() = (lastTouchPos - bounds.pos).clampMag(bounds.radius)
    private val dir: Vector
        get() = headDeltaPos/bounds.radius

    final override fun touchDown(screenPos: Vector, fingerID: Int) {
        if (touchIsOnJoystick(screenPos)) {
            touchedFingerID = fingerID
            lastTouchPos = screenPos
            onPressed(dir)
            onDirChanged(dir)
        }
    }

    final override fun touchRelease(screenPos: Vector, fingerID: Int) {
        if (fingerID == touchedFingerID) {
            onReleased(dir)
            touchedFingerID = null
            lastTouchPos = bounds.pos
            onDirChanged(dir)
        }
    }

    final override fun touchMove(screenPos: Vector, fingerID: Int) {
        if (fingerID == touchedFingerID) {
            lastTouchPos = screenPos
            onDirChanged(dir)
        }
    }

    abstract fun onDirChanged(dir: Vector)
    abstract fun onPressed(dir: Vector)
    abstract fun onReleased(dir: Vector)

    private fun touchIsOnJoystick(touchPos: Vector): Boolean {
        return bounds collidingWith Point(touchPos)
    }

    final override fun draw(canvas: Canvas) {
        SharedPaint.color = Color.GRAY
        SharedPaint.alpha = 200
        canvas.drawCircle(bounds.pos.x, bounds.pos.y, bounds.radius, SharedPaint)
        val head: Vector = bounds.pos+headDeltaPos
        canvas.drawCircle(head.x, head.y, bounds.radius/2, SharedPaint)
        SharedPaint.alpha = 255
    }
}