package com.oggtechnologies.salvos.model

import com.oggtechnologies.salvos.gamerunner.Updater
import com.oggtechnologies.salvos.model.entities.DefaultEntity
import com.oggtechnologies.salvos.model.entities.Player
import com.oggtechnologies.salvos.model.map.TileMap
import com.oggtechnologies.salvos.utilities.Vector

class DefaultModel : Updater, ModelViewer, ModelController {
    override val tileMap = TileMap()
    override val player: Player = Player(DefaultEntity(Vector(0F, 0F), Vector(0.01F, 0F), Vector()))

    override fun update() {
        player.update()
    }

    override fun move(dir: Vector) {
        player.vel = dir
    }
}