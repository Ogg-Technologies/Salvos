package com.oggtechnologies.salvos.model.map

import com.oggtechnologies.salvos.model.map.tiles.Tile
import com.oggtechnologies.salvos.model.map.tiles.TileFactory

class TileMap {
    private val mapSize = 10
    private val tileMatrix: Array<Array<Tile>> = Array(mapSize) {
        y -> Array(mapSize) {
            x -> if ((x+y)%3==0) TileFactory.createGround() else TileFactory.createWall()
        }
    }

    fun getTile(x: Int, y: Int) : Tile {
        return tileMatrix[y][x]
    }
}