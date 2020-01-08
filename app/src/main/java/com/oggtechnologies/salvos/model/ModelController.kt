package com.oggtechnologies.salvos.model

import com.oggtechnologies.salvos.utilities.Vector

interface ModelController {
    fun move(dir: Vector)
    fun startChargeGun(dir: Vector)
    fun shootGun(dir: Vector)
    fun aimGun(dir: Vector)
}