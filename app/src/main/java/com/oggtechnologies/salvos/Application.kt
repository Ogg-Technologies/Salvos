package com.oggtechnologies.salvos

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.SurfaceView
import com.oggtechnologies.salvos.controller.Controller
import com.oggtechnologies.salvos.gamerunner.DefaultGameRunner
import com.oggtechnologies.salvos.gamerunner.GameRunner
import com.oggtechnologies.salvos.gamerunner.Renderer
import com.oggtechnologies.salvos.model.DefaultModel
import com.oggtechnologies.salvos.utilities.Vector
import com.oggtechnologies.salvos.view.View

class Application(context: Context) : SurfaceView(context),
    Renderer {
    private val model: DefaultModel = DefaultModel()
    private val gameRunner: GameRunner = DefaultGameRunner(model, this)
    private var firstFrame = true
    private var screenSize: Vector? = null

    private var controller: Controller? = null
    private var view: View? = null

    fun resume() {
        hideAndroidUI()
        gameRunner.start()
    }

    fun pause() {
        gameRunner.stop()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        controller?.onTouchEvent(event)
        return true
    }

    override fun render() {
        if (holder.surface.isValid) {
            val canvas = holder.lockCanvas()
            if (firstFrame) {
                firstFrameInitializations(canvas)
                firstFrame = false
            }

            canvas.drawColor(Color.DKGRAY)
            view?.draw(canvas)
            controller?.draw(canvas)
            drawFpsUps(canvas)

            holder.unlockCanvasAndPost(canvas)
        }
    }

    private fun firstFrameInitializations(canvas: Canvas) {
        val screenSize = Vector(width.toFloat(), height.toFloat())
        controller = Controller(model, screenSize)
        view = View(model, screenSize)
        this.screenSize = screenSize
    }

    private fun hideAndroidUI() {
        systemUiVisibility =
            android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
    }

    private fun drawFpsUps(canvas: Canvas) {
        val paint = Paint()
        paint.color = Color.WHITE
        paint.textSize = 40F
        canvas.drawText("ups: ${gameRunner.ups} fps: ${gameRunner.fps}", 60F, 50F, paint)
    }
}