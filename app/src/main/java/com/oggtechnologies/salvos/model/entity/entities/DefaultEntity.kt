package com.oggtechnologies.salvos.model.entity.entities

import com.oggtechnologies.salvos.model.WorldService
import com.oggtechnologies.salvos.utilities.Vector
import com.oggtechnologies.salvos.utilities.collidingWith
import com.oggtechnologies.salvos.utilities.shapes.implementations.Circle
import com.oggtechnologies.salvos.utilities.shapes.implementations.Square
import com.oggtechnologies.salvos.utilities.shapes.interfaces.Shape
import kotlin.math.floor

class DefaultEntity(override var pos: Vector, private val worldService: WorldService) : Entity {

    override val bounds: Circle
        get() = Circle(pos, 0.3F)

    override var vel: Vector = Vector()
    override fun update() {
        val nextBounds = Circle(pos+vel, 0.3F)
        if (!isColliding(nextBounds)) {
            pos += vel
        }
    }

    override fun applyForce(force: Vector) {
        vel = force
    }

    private fun isColliding(b: Circle): Boolean {
        val xRange = floor(b.pos.x-b.radius).toInt() .. floor(b.pos.x+b.radius).toInt()
        val yRange = floor(b.pos.y-b.radius).toInt() .. floor(b.pos.y+b.radius).toInt()
        for (x in xRange) {
            for (y in yRange) {
                if (!worldService.canGoThrough(x, y)) {
                    if (b collidingWith Square(Vector(x.toFloat(), y.toFloat()), 1F)) {
                        return true
                    }
                }
            }
        }
        return false
    }

}