package com.oggtechnologies.salvos.model.map.tiles

object TileFactory {
    fun createGround() : Tile {
        return Ground()
    }

    fun createWall(): Tile {
        return Wall()
    }
}