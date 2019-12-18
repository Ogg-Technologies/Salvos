package com.oggtechnologies.salvos.gamerunner

import android.util.Log
import com.oggtechnologies.salvos.model.Updater
import com.oggtechnologies.salvos.view.Representer

class DefaultGameRunner(private val updater: Updater, private val representer: Representer) :
    GameRunner {
    @Volatile
    private var playing = true
    private var gameThread: Thread = Thread(this)

    override fun stop() {
        playing = false
        try {
            gameThread.join()
        } catch (e: InterruptedException) {
            Log.e("Error:", "joining thread")
        }
    }

    override fun start() {
        playing = true
        gameThread = Thread(this)
        gameThread.start()
    }

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
                updater.update()
                updates++
                delta--
            }
            representer.represent()
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
}