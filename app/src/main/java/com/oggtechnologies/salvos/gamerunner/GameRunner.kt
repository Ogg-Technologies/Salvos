package com.oggtechnologies.salvos.gamerunner

interface GameRunner : Runnable {
    fun stop()
    fun start()
}