package com.oggtechnologies.salvos.model

import com.oggtechnologies.salvos.model.entity.entities.Entity
import com.oggtechnologies.salvos.model.entity.entities.mobs.Player
import com.oggtechnologies.salvos.model.map.TileMap

interface ModelViewer {
    val tileMap: TileMap
    val player: Player
    val projectiles: MutableList<Entity>
}