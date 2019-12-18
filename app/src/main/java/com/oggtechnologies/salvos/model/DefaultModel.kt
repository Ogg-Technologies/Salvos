package com.oggtechnologies.salvos.model

class DefaultModel : Updater, ModelViewer, ModelController {
    override var x = 0
        private set
    private var vel = 3

    override fun update() {
        x += vel
    }

    override fun changeDirection() {
        vel = -vel
    }
}