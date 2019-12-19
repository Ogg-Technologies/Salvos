package com.oggtechnologies.salvos.model

import com.oggtechnologies.salvos.model.map.TileMap
import com.oggtechnologies.salvos.model.map.tiles.Tile

interface ModelViewer {
    val tileMap: TileMap
}