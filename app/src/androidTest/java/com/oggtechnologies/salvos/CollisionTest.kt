package com.oggtechnologies.salvos

import com.oggtechnologies.salvos.utilities.Vector
import com.oggtechnologies.salvos.utilities.collidingWith
import com.oggtechnologies.salvos.utilities.shapes.implementations.Circle
import com.oggtechnologies.salvos.utilities.shapes.implementations.Point
import com.oggtechnologies.salvos.utilities.shapes.implementations.Rectangle
import com.oggtechnologies.salvos.utilities.shapes.implementations.Square

import org.junit.Test

import org.junit.Assert.*

class CollisionTest {
    @Test
    fun pointPointCollision() {
        val p1 = Point(Vector(3F, 2F))
        val p2 = Point(Vector(3F, 2F))
        assertTrue(p1 collidingWith p2)

        val p3 = Point(Vector(3F, 2F))
        val p4 = Point(Vector(2F, 2F))
        assertFalse(p3 collidingWith p4)
    }

    @Test
    fun rectangleRectangleCollision() {
        val r1 = Rectangle(Vector(), Vector(2F, 2F))
        val r2 = Rectangle(Vector(1F, 1F), Vector(2F, 2F))
        assertTrue(r1 collidingWith r2)

        val r3 = Rectangle(Vector(), Vector(1F, 1F))
        val r4 = Rectangle(Vector(2F, 2F), Vector(2F, 2F))
        assertFalse(r3 collidingWith r4)
    }

    @Test
    fun rectangleSquareCollision() {
        val r1 = Rectangle(Vector(), Vector(2F, 2F))
        val s1 = Square(Vector(1F, 1F), 1F)
        assertTrue(r1 collidingWith s1)

        val r2 = Rectangle(Vector(), Vector(1F, 1F))
        val s2 = Square(Vector(2F, 2F), 1F)
        assertFalse(r2 collidingWith s2)
    }

    @Test
    fun rectanglePointCollision() {
        val r1 = Rectangle(Vector(), Vector(2F, 2F))
        val p1 = Point(Vector(1F, 1F))
        assertTrue(r1 collidingWith p1)

        val r2 = Rectangle(Vector(), Vector(1F, 1F))
        val p2 = Point(Vector(2F, 2F))
        assertFalse(p2 collidingWith r2)
    }

    @Test
    fun circlePointCollision() {
        val c1 = Circle(Vector(0F, 0F), 2F)
        val p1 = Point(Vector(1F, 1F))
        assertTrue(c1 collidingWith p1)

        val c2 = Circle(Vector(0F, 0F), 1F)
        val p2 = Point(Vector(1F, 1F))
        assertFalse(c2 collidingWith p2)
    }

    @Test
    fun circleCircleCollision() {
        val c1 = Circle(Vector(0F, 0F), 2F)
        val c2 = Circle(Vector(1F, 2F), 2F)
        assertTrue(c1 collidingWith c2)

        val c3 = Circle(Vector(0F, 0F), 1F)
        val c4 = Circle(Vector(1F, 2F), 1F)
        assertFalse(c3 collidingWith c4)
    }

    @Test
    fun circleRectangleCollision() {
        val c1 = Circle(Vector(0F, 0F), 2F)
        val r1 = Rectangle(Vector(1F, 1F), Vector(1F, 2F))
        assertTrue(c1 collidingWith r1)

        val c2 = Circle(Vector(0F, 0F), 1F)
        val r2 = Rectangle(Vector(1F, 1F), Vector(1F, 2F))
        assertFalse(r2 collidingWith c2)

        val c3 = Circle(Vector(0F, 0F), 1F)
        val r3 = Rectangle(Vector(1F, 0.1F), Vector(1F, 2F))
        assertFalse(r3 collidingWith c3)
    }
}
