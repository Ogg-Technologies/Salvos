package com.oggtechnologies.salvos.model.entities

import com.oggtechnologies.salvos.utilities.Vector

class DefaultMob(private val baseEntity: Entity) : Mob, Entity by baseEntity {
    override var goalVel: Vector = Vector()
    override fun update() {
        println("vel: $vel, goalVel: $goalVel")
        val delta = goalVel-vel
        val clampedDelta = delta.clampMag(0.02F)
        vel += clampedDelta

        baseEntity.update()
    }
}
