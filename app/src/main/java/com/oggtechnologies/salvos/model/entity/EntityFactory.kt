package com.oggtechnologies.salvos.model.entity

import com.oggtechnologies.salvos.model.WorldService
import com.oggtechnologies.salvos.model.entity.entities.DefaultEntity
import com.oggtechnologies.salvos.model.entity.entities.DefaultMob
import com.oggtechnologies.salvos.model.entity.entities.Player
import com.oggtechnologies.salvos.utilities.Vector

class EntityFactory(private val worldService: WorldService) {
    fun createPlayer(x: Float, y: Float): Player {
        return Player(DefaultMob(DefaultEntity(Vector(x, y), 0.5F, worldService)))
    }
}