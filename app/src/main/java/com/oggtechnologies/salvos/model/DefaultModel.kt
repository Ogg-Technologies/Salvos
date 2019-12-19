package com.oggtechnologies.salvos.model

import com.oggtechnologies.salvos.gamerunner.Updater
import com.oggtechnologies.salvos.model.map.TileMap

class DefaultModel : Updater, ModelViewer, ModelController {
    override val tileMap = TileMap()

    override fun update() {
    }
}