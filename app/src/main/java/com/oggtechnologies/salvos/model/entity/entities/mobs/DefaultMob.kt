package com.oggtechnologies.salvos.model.entity.entities.mobs

import com.oggtechnologies.salvos.model.entity.entities.Entity
import com.oggtechnologies.salvos.utilities.Vector

class DefaultMob(private val baseEntity: Entity) : Mob, Entity by baseEntity {
    /**
     * A measure for how well the mob can change velocity
     */
    private val agility = 0.01F

    /**
     * The velocity that the mob will try to steer towards
     */
    override var goalVel: Vector = Vector()
    override fun update() {
        val delta = goalVel-vel

        val clampedDelta = delta.clampMag(agility)
        vel += clampedDelta

        baseEntity.update()
    }
}
