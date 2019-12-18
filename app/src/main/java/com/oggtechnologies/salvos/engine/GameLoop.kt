package com.example.salvos.engine

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.Toast
import com.oggtechnologies.salvos.MainActivity


/*
class GameLoop(context: Context?) : SurfaceView(context), Runnable {
    private var gameThread: Thread? = null
    private val ourHolder: SurfaceHolder = holder
    @Volatile
    private var playing: Boolean
    var firstFrame = true
    override fun run() {
        var lastTime = System.nanoTime()
        var timer = System.currentTimeMillis()
        val ns = 1000000000.0 / 60.0
        var delta = 0.0
        var frames = 0
        var updates = 0
        while (playing) {
            val now = System.nanoTime()
            delta += (now - lastTime) / ns
            lastTime = now
            // Runs 60 times each second
            while (delta >= 1) {
                UpdateHandler.update()
                updates++
                delta--
            }
            preDraw()
            frames++
            // Runs 1 time each second
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000
                //System.out.println(updates + " ups, " + frames + " fps");
                updates = 0
                frames = 0
            }
        }
    }

    private fun preDraw() {
        if (ourHolder.surface.isValid) {
            MainActivity.canvas = ourHolder.lockCanvas()
            if (firstFrame) {
                Initializer.firstFrame(context)
                firstFrame = false
            }
            DrawHandler.draw()
            ourHolder.unlockCanvasAndPost(MainActivity.canvas)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        TouchEventHandler.onTouch(event)
        return true
    }

    fun pause() {
        playing = false
        try {
            gameThread!!.join()
        } catch (e: InterruptedException) {
            Log.e("Error:", "joining thread")
        }
    }

    fun resume() {
        hideAndroidUI()
        playing = true
        gameThread = Thread(this)
        gameThread!!.start()
    }

    private fun hideAndroidUI() {
        systemUiVisibility =
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
    }

    init {
        Initializer.setUp(resources)
        playing = true
        hideAndroidUI()
    }
}

 */
