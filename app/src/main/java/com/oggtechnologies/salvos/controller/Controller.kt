package com.oggtechnologies.salvos.controller

import android.view.MotionEvent
import com.oggtechnologies.salvos.model.ModelController

class Controller(private val model: ModelController) {
    fun onTouchEvent(event: MotionEvent): Boolean {
        println("Touch event happened")
        when (event.action) {
            MotionEvent.ACTION_DOWN -> model.changeDirection()
        }

        return true
    }
}