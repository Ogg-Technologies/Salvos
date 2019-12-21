package com.oggtechnologies.salvos.controller

import android.graphics.Canvas
import android.view.MotionEvent
import com.oggtechnologies.salvos.model.ModelController
import com.oggtechnologies.salvos.utilities.Vector

class Controller(private val model: ModelController) {

    fun draw(canvas: Canvas, screenSize: Vector) {

    }

    /**
     * Handles every motion event
     * @param event     MotionEvent triggered by the user
     */
    fun onTouchEvent(event: MotionEvent) {
        val fingerIndex = event.actionIndex
        val fingerID = event.getPointerId(fingerIndex)
        val screenX = event.getX(fingerIndex)
        val screenY = event.getY(fingerIndex)
        val screenPos = Vector(screenX, screenY)
        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> touchDown(
                screenPos,
                fingerID
            )
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> touchRelease(
                screenPos,
                fingerID
            )
            MotionEvent.ACTION_MOVE -> touchMove(screenPos, fingerID)
        }
    }

    private fun touchDown(screenPos: Vector, fingerID: Int) {}

    private fun touchRelease(screenPos: Vector, fingerID: Int) {}

    private fun touchMove(screenPos: Vector, fingerID: Int) {}
}