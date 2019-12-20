package com.oggtechnologies.salvos.model

import com.oggtechnologies.salvos.model.entities.Player
import com.oggtechnologies.salvos.model.map.TileMap

interface ModelViewer {
    val tileMap: TileMap
    val player: Player
}