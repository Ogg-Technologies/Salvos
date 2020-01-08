package com.oggtechnologies.salvos.utilities

import com.oggtechnologies.salvos.utilities.shapes.implementations.Circle
import com.oggtechnologies.salvos.utilities.shapes.interfaces.Box
import com.oggtechnologies.salvos.utilities.shapes.implementations.Point
import kotlin.math.abs
import kotlin.math.pow

// Point Collision
infix fun Point.collidingWith(p: Point): Boolean {
    return Collision.isColliding(this, p)
}

infix fun Point.collidingWith(b: Box): Boolean {
    return Collision.isColliding(b, this)
}

infix fun Point.collidingWith(c: Circle): Boolean {
    return Collision.isColliding(c, this)
}

// Box Collision
infix fun Box.collidingWith(b: Box): Boolean {
    return Collision.isColliding(this, b)
}

infix fun Box.collidingWith(p: Point): Boolean {
    return Collision.isColliding(this, p)
}

infix fun Box.collidingWith(c: Circle): Boolean {
    return Collision.isColliding(c, this)
}

// Circle Collision
infix fun Circle.collidingWith(p: Point): Boolean {
    return Collision.isColliding(this, p)
}

infix fun Circle.collidingWith(c: Circle): Boolean {
    return Collision.isColliding(this, c)
}

infix fun Circle.collidingWith(b: Box): Boolean {
    return Collision.isColliding(this, b)
}

private object Collision {
    fun isColliding(p1: Point, p2: Point): Boolean {
        return p1.pos == p2.pos
    }

    fun isColliding(b1: Box, b2: Box): Boolean {
        if (b1.right>=b2.left && b1.left<=b2.right) {
            // Colliding in x-axis
            if (b1.bottom>=b2.top && b1.top<=b2.bottom) {
                // Colliding in y-axis
                return true
            }
        }
        return false
    }

    fun isColliding(b: Box, p: Point): Boolean {
        if (b.right>=p.pos.x && b.left<=p.pos.x) {
            // Colliding in x-axis
            if (b.bottom>=p.pos.y && b.top<=p.pos.y) {
                // Colliding in y-axis
                return true
            }
        }
        return false
    }

    fun isColliding(c: Circle, p: Point): Boolean {
        return (c.pos-p.pos).mag <= c.radius
    }

    fun isColliding(c1: Circle, c2: Circle): Boolean {
        return (c1.pos-c2.pos).mag <= c1.radius+c2.radius
    }

    fun isColliding(c: Circle, b: Box): Boolean {
        val centersDelta = c.pos - b.center
        val distX = abs(centersDelta.x)
        val distY = abs(centersDelta.y)

        if (distX > b.width/2 + c.radius) { return false }
        if (distY > b.height/2 + c.radius) { return false }

        if (distX <= (b.width/2)) { return true }
        if (distY <= (b.height/2)) { return true }

        var dx=distX-b.width/2
        var dy=distY-b.height/2
        return (dx*dx+dy*dy<= c.radius.toDouble().pow(2.0))
    }
}