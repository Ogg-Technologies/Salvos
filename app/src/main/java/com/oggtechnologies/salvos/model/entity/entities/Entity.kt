package com.oggtechnologies.salvos.model.entity.entities

import com.oggtechnologies.salvos.utilities.Vector
import com.oggtechnologies.salvos.utilities.shapes.implementations.Circle
import com.oggtechnologies.salvos.utilities.shapes.interfaces.Shape

interface Entity : Shape {
    var vel: Vector
    val bounds: Circle
    fun applyForce(force: Vector)
    fun update()
}