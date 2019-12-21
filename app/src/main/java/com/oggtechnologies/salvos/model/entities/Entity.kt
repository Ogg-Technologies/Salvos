package com.oggtechnologies.salvos.model.entities

import com.oggtechnologies.salvos.utilities.Vector

interface Entity {
    var pos: Vector
    var vel: Vector
    var size: Vector
    fun update()
}