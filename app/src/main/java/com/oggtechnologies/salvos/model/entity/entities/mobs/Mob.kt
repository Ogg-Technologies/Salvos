package com.oggtechnologies.salvos.model.entity.entities.mobs

import com.oggtechnologies.salvos.model.entity.entities.Entity
import com.oggtechnologies.salvos.utilities.Vector

interface Mob : Entity {
    var goalVel: Vector
}
