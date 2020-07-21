package com.oggtechnologies.salvos.model

import com.oggtechnologies.salvos.gamerunner.Updater
import com.oggtechnologies.salvos.model.entity.EntityFactory
import com.oggtechnologies.salvos.model.entity.entities.Entity
import com.oggtechnologies.salvos.model.entity.entities.mobs.Player
import com.oggtechnologies.salvos.model.map.TileMap
import java.util.*
import kotlin.collections.ArrayList

class DefaultModel : Updater, ModelViewer, ModelController {
    private val commands: Queue<ControllerCommand> = ArrayDeque()
    override val tileMap = TileMap()
    override val projectiles: MutableList<Entity> = ArrayList()
    private val worldService = WorldService(tileMap, projectiles)
    private val entityFactory = EntityFactory(worldService)
    override val player: Player = entityFactory.createPlayer(1.5F, 1.5F)


    override fun update() {
        executeCommands()
        player.update()
        for (p in projectiles) {
            p.update()
        }
    }

    private fun executeCommands() {
        synchronized(commands) {
            while (commands.size > 0) {
                val command = commands.remove()
                executeCommand(command)
            }
        }
    }

    private fun executeCommand(command: ControllerCommand) {
        when (command) {
            is Move -> player.goalVel = command.dir
            is StartChargeGun -> player.startChargeGun(command.dir)
            is ShootGun -> player.shootGun(command.dir)
            is AimGun -> player.aimGun(command.dir)
        }
    }

    override fun sendCommand(command: ControllerCommand) {
        synchronized(commands) {
            commands.add(command)
        }
    }
}