package com.oggtechnologies.salvos.model.entities

import com.oggtechnologies.salvos.utilities.Vector
import com.oggtechnologies.salvos.utilities.shapes.Shape

interface Entity : Shape {
    var vel: Vector
    fun applyForce(force: Vector)
    fun update()
}