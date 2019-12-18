package com.oggtechnologies.salvos

import android.content.Context
import android.view.MotionEvent
import com.oggtechnologies.salvos.controller.Controller
import com.oggtechnologies.salvos.gamerunner.DefaultGameRunner
import com.oggtechnologies.salvos.gamerunner.GameRunner
import com.oggtechnologies.salvos.model.DefaultModel
import com.oggtechnologies.salvos.view.View

class Application(context: Context) {
    private val model: DefaultModel = DefaultModel()
    private val controller: Controller = Controller(model)
    val view: View = View(context, model)
    private val gameRunner: GameRunner = DefaultGameRunner(model, view)

    fun resume() {
        view.hideAndroidUI()
        gameRunner.start()
    }

    fun pause() {
        gameRunner.stop()
    }

    fun onTouchEvent(event: MotionEvent): Boolean {
        return controller.onTouchEvent(event)
    }
}