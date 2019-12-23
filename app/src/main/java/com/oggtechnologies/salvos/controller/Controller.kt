package com.oggtechnologies.salvos.controller

import android.graphics.Canvas
import android.graphics.Color
import android.view.MotionEvent
import com.oggtechnologies.salvos.controller.gui.AbstractJoystick
import com.oggtechnologies.salvos.controller.gui.GUIElement
import com.oggtechnologies.salvos.model.ModelController
import com.oggtechnologies.salvos.utilities.SharedPaint
import com.oggtechnologies.salvos.utilities.Vector

class Controller(private val model: ModelController, private val screenSize: Vector) {

    private val guiElements: MutableList<GUIElement> = ArrayList()

    init {
        val joystick: AbstractJoystick = object : AbstractJoystick(Vector(300F, screenSize.y-300F), 150F) {
            override fun onDirChanged(dir: Vector) {
                model.move(dir/800F)
            }
        }
        guiElements.add(joystick)

    }

    fun draw(canvas: Canvas) {
        for (e in guiElements) {
            e.draw(canvas)
        }
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

    private fun touchDown(screenPos: Vector, fingerID: Int) {
        for (e in guiElements) {
            e.touchDown(screenPos, fingerID)
        }
    }

    private fun touchRelease(screenPos: Vector, fingerID: Int) {
        for (e in guiElements) {
            e.touchRelease(screenPos, fingerID)
        }
    }

    private fun touchMove(screenPos: Vector, fingerID: Int) {
        for (e in guiElements) {
            e.touchMove(screenPos, fingerID)
        }
    }
}