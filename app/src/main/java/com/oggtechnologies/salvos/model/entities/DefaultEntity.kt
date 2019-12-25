package com.oggtechnologies.salvos.model.entities

import com.oggtechnologies.salvos.utilities.Vector
import com.oggtechnologies.salvos.utilities.shapes.implementations.Circle
import com.oggtechnologies.salvos.utilities.shapes.interfaces.Shape

class DefaultEntity(override var pos: Vector, override var vel: Vector = Vector()) : Entity, Shape by Circle(
    pos,
    1F
) {
    override fun update() {
        pos += vel
    }

    override fun applyForce(force: Vector) {
        vel = force
    }

}