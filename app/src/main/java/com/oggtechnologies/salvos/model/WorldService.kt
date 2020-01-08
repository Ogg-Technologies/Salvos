package com.oggtechnologies.salvos.model

import com.oggtechnologies.salvos.model.entity.entities.Entity
import com.oggtechnologies.salvos.model.map.TileMap

class WorldService(private val tileMap: TileMap, private val projectiles: MutableList<Entity>) {
    fun canGoThrough(x: Int, y: Int): Boolean {
        if (!isWithinMap(x, y)) {
            return false
        }
        return tileMap.getTile(x, y).canGoThrough
    }

    fun isWithinMap(x: Int, y: Int): Boolean {
        if (x<0 || x>=tileMap.mapSize) return false
        if (y<0 || y>=tileMap.mapSize) return false
        return true
    }

    fun addProjectile(projectile: Entity) {
        projectiles.add(projectile)
    }
}