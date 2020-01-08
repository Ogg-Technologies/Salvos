package com.oggtechnologies.salvos.model

import com.oggtechnologies.salvos.gamerunner.Updater
import com.oggtechnologies.salvos.model.entity.EntityFactory
import com.oggtechnologies.salvos.model.entity.entities.Entity
import com.oggtechnologies.salvos.model.entity.entities.mobs.Player
import com.oggtechnologies.salvos.model.map.TileMap
import com.oggtechnologies.salvos.utilities.Vector

class DefaultModel : Updater, ModelViewer, ModelController {
    override val tileMap = TileMap()
    override val projectiles: MutableList<Entity> = ArrayList()
    private val worldService = WorldService(tileMap, projectiles)
    private val entityFactory = EntityFactory(worldService)
    override val player: Player = entityFactory.createPlayer(1.5F, 1.5F)


    override fun update() {
        player.update()
        for (p in projectiles) {
            p.update()
        }
    }

    override fun move(dir: Vector) {
        player.goalVel = dir
    }

    override fun startChargeGun(dir: Vector) {
        player.startChargeGun(dir)
    }

    override fun shootGun(dir: Vector) {
        player.shootGun(dir)
    }

    override fun aimGun(dir: Vector) {
        player.aimGun(dir)
    }
}