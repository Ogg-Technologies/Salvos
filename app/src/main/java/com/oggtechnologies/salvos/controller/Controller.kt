package com.oggtechnologies.salvos.controller

import android.graphics.Canvas
import android.view.MotionEvent
import com.oggtechnologies.salvos.controller.gui.AbstractJoystick
import com.oggtechnologies.salvos.controller.gui.GUIElement
import com.oggtechnologies.salvos.model.*
import com.oggtechnologies.salvos.utilities.Vector

class Controller(private val model: ModelController, private val screenSize: Vector) {

    private val guiElements: MutableList<GUIElement> = ArrayList()

    init {
        val movementJoystick: AbstractJoystick =
            object : AbstractJoystick(Vector(500F, screenSize.y - 500F), 150F) {
                override fun onDirChanged(dir: Vector) {
                    model.sendCommand(Move(dir / 10F))
                }

                override fun onPressed(dir: Vector) {}
                override fun onReleased(dir: Vector) {}
            }
        guiElements.add(movementJoystick)

        val gunJoystick: AbstractJoystick =
            object : AbstractJoystick(Vector(screenSize.x - 500F, screenSize.y - 500F), 150F) {
                override fun onDirChanged(dir: Vector) {
                    model.sendCommand(AimGun(dir))
                }
                override fun onPressed(dir: Vector) {
                    model.sendCommand(StartChargeGun(dir))
                }
                override fun onReleased(dir: Vector) {
                    model.sendCommand(ShootGun(dir))
                }

            }
        guiElements.add(gunJoystick)
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

        fun getScreenPos(fingerIndex: Int): Vector {
            val screenX = event.getX(fingerIndex)
            val screenY = event.getY(fingerIndex)
            return Vector(screenX, screenY)
        }

        val action = event.action and MotionEvent.ACTION_MASK

        if (action == MotionEvent.ACTION_MOVE) {
            /**
             * Android doesn't provide actionIndex when it is a MOVE event. Therefore we call
             * touchMove on all pointers when a finger moves.
             */
            for (index in 0 until event.pointerCount) {
                val pos = getScreenPos(index)
                val id = event.getPointerId(index)
                touchMove(pos, id)
            }
        } else {
            val index = event.actionIndex
            val pos = getScreenPos(index)
            val id = event.getPointerId(index)
            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                    touchDown(pos, id)
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                    touchRelease(pos, id)
                }
            }
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