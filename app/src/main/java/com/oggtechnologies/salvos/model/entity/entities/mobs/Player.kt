package com.oggtechnologies.salvos.model.entity.entities.mobs

import com.oggtechnologies.salvos.model.entity.entities.projectiles.Bullet
import com.oggtechnologies.salvos.model.weapons.Gun
import com.oggtechnologies.salvos.utilities.Vector

class Player(private val baseMob: Mob): Mob by baseMob {
    private val gun = object : Gun() {
        override fun shoot(vel: Vector) {
            val bullet = Bullet(pos, vel, worldService)
            worldService.addProjectile(bullet)
        }
    }

    override fun update() {
        gun.update()
        baseMob.update()
    }

    fun startChargeGun(dir: Vector) {
        gun.startChargeGun(dir)
    }

    fun shootGun(dir: Vector) {
        gun.shootGun(dir)
    }

    fun aimGun(dir: Vector) {
        gun.aim(dir)
    }
}
