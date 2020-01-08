package com.oggtechnologies.salvos.model.entity.entities

import com.oggtechnologies.salvos.model.WorldService
import com.oggtechnologies.salvos.utilities.Vector
import com.oggtechnologies.salvos.utilities.collidingWith
import com.oggtechnologies.salvos.utilities.shapes.implementations.Square
import java.lang.IllegalArgumentException
import kotlin.math.ceil
import kotlin.math.floor

class DefaultEntity(
    override var pos: Vector,
    private val size: Float,
    override val worldService: WorldService,
    override var vel: Vector = Vector()
) : Entity {

    override val bounds: Square
        get() = Square.byCenter(pos, size)

    override fun update() {
        singleAxisMove(Vector(vel.x, 0F))
        singleAxisMove(Vector(0F, vel.y))
    }

    private fun singleAxisMove(delta: Vector) {
        if (delta.x != 0F && delta.y != 0F) {
            throw IllegalArgumentException("Can only move one axis at a time")
        }

        val nextBounds = Square.byCenter(pos + delta, size)
        if (!isColliding(nextBounds)) {
            pos += delta
        } else {
            when {
                delta.x != 0F -> vel = vel.setX(0F)
                delta.y != 0F -> vel = vel.setY(0F)
            }
            singleAxisMoveToEdge(delta)
        }
    }

    private fun singleAxisMoveToEdge(delta: Vector) {
        if (delta.x != 0F && delta.y != 0F) {
            throw IllegalArgumentException("Can only move one axis at a time")
        }

        val curX = pos.x
        val curY = pos.y
        var newX = curX
        var newY = curY
        val halfSize = size / 2
        val wallPadding = 0.001F
        when {
            delta.x > 0 -> newX = ceil(curX) - halfSize - wallPadding
            delta.x < 0 -> newX = floor(curX) + halfSize + wallPadding
            delta.y > 0 -> newY = ceil(curY) - halfSize - wallPadding
            delta.y < 0 -> newY = floor(curY) + halfSize + wallPadding
        }

        pos = Vector(newX, newY)
    }

    override fun applyForce(force: Vector) {
        vel = force
    }

    private fun isColliding(b: Square): Boolean {
        val xRange = floor(b.pos.x - b.size).toInt()..floor(b.pos.x + b.size).toInt()
        val yRange = floor(b.pos.y - b.size).toInt()..floor(b.pos.y + b.size).toInt()
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