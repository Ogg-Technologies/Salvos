package com.oggtechnologies.salvos.model.map

import com.oggtechnologies.salvos.model.map.tiles.Tile
import com.oggtechnologies.salvos.model.map.tiles.TileFactory

class TileMap {
    val mapSize = 20
    private val tileMatrix: Array<Array<Tile>> = Array(mapSize) {
        y -> Array(mapSize) {
            x -> if (Math.random()<0.1) TileFactory.createWall() else TileFactory.createGround()
        }
    }

    fun getTile(x: Int, y: Int) : Tile {
        return tileMatrix[y][x]
    }
}