package com.oggtechnologies.salvos.model.weapons

import com.oggtechnologies.salvos.utilities.Vector

abstract class Gun {
    var dir = Vector(1F, 0F)
        private set

    val chargeFraction: Float
        get() = charge/maxCharge

    private var charge = 0F
    private val maxCharge = 0.5F
    private val chargeRate = 0.010F
    private var isCharging = false

    fun update() {
        if (isCharging) {
            chargeGun()
        }
    }

    private fun chargeGun() {
        charge += chargeRate
        if (charge > maxCharge) {
            charge = maxCharge
        }
    }

    fun startChargeGun(dir: Vector) {
        isCharging = true
        aim(dir)
    }

    fun shootGun(dir: Vector) {
        isCharging = false
        aim(dir)
        shoot(dir * charge)
        charge = 0F
    }

    fun aim(dir: Vector) {
        this.dir = dir.clampMag(1F)
    }

    abstract fun shoot(vel: Vector)
}
