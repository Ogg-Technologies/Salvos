package com.oggtechnologies.salvos.model

import com.oggtechnologies.salvos.gamerunner.Updater
import com.oggtechnologies.salvos.model.entity.EntityFactory
import com.oggtechnologies.salvos.model.entity.entities.DefaultEntity
import com.oggtechnologies.salvos.model.entity.entities.DefaultMob
import com.oggtechnologies.salvos.model.entity.entities.Player
import com.oggtechnologies.salvos.model.map.TileMap
import com.oggtechnologies.salvos.utilities.Vector

class DefaultModel : Updater, ModelViewer, ModelController {
    override val tileMap = TileMap()
    private val entityFactory = EntityFactory(WorldService(tileMap))
    override val player: Player = entityFactory.createPlayer(2F, 2F)

    override fun update() {
        player.update()
    }

    override fun move(dir: Vector) {
        player.goalVel = dir
    }
}