package com.oggtechnologies.salvos.gamerunner

interface GameRunner : Runnable {
    val fps: Int
    val ups: Int
    fun stop()
    fun start()
}