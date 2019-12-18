package com.example.salvos.engine

import android.view.MotionEvent


internal object TouchEventHandler {
    /**
     * On touch controller, handles every motion event
     * @param event     MotionEvent triggered by the user
     */
    fun onTouch(event: MotionEvent) {
        val fingerIndex = event.actionIndex
        val fingerID = event.getPointerId(fingerIndex)
        val screenX = event.getX(fingerIndex).toInt()
        val screenY = event.getY(fingerIndex).toInt()
        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> touchDown(
                screenX,
                screenY,
                fingerID
            )
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> touchRelease(
                screenX,
                screenY,
                fingerID
            )
            MotionEvent.ACTION_MOVE -> touchMove(screenX, screenY, fingerID)
        }
    }

    /**
     * The screen was touched down
     * @param screenX   Screen x coordinate
     * @param screenY   Screen y coordinate
     * @param fingerID  Finger ID
     */
    private fun touchDown(screenX: Int, screenY: Int, fingerID: Int) {}

    /**
     * Called when a touch was released, eg a button was pressed
     * @param screenX   Screen x coordinate
     * @param screenY   Screen y coordinate
     * @param fingerID  Finger ID
     */
    private fun touchRelease(screenX: Int, screenY: Int, fingerID: Int) {}

    /**
     * Called when a touch was moved
     * @param screenX   Screen x coordinate
     * @param screenY   Screen y coordinate
     * @param fingerID  Finger ID
     */
    private fun touchMove(screenX: Int, screenY: Int, fingerID: Int) {}
}

