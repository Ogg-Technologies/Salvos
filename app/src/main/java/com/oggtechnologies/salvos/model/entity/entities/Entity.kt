package com.oggtechnologies.salvos.model.entity.entities

import com.oggtechnologies.salvos.utilities.Vector
import com.oggtechnologies.salvos.utilities.shapes.implementations.Circle
import com.oggtechnologies.salvos.utilities.shapes.implementations.Square
import com.oggtechnologies.salvos.utilities.shapes.interfaces.Shape

interface Entity {
    var pos: Vector
    var vel: Vector
    val bounds: Square
    fun applyForce(force: Vector)
    fun update()
}