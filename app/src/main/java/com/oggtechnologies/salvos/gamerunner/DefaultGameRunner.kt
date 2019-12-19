package com.oggtechnologies.salvos.gamerunner

import android.util.Log

class DefaultGameRunner(private val updater: Updater, private val renderer: Renderer) :
    GameRunner {

    override var fps: Int = 0
        private set
    override var ups: Int = 0
        private set


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
            renderer.render()
            frames++
            // Runs 1 time each second
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000
                //System.out.println(updates + " ups, " + frames + " fps");
                ups = updates
                updates = 0
                fps = frames
                frames = 0
            }
        }
    }
}