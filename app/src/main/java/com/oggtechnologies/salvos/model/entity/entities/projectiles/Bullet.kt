package com.oggtechnologies.salvos.model.entity.entities.projectiles

import com.oggtechnologies.salvos.model.WorldService
import com.oggtechnologies.salvos.model.entity.entities.DefaultEntity
import com.oggtechnologies.salvos.model.entity.entities.Entity
import com.oggtechnologies.salvos.utilities.Vector

class Bullet(pos: Vector, vel: Vector, worldService: WorldService): Entity by DefaultEntity(pos, 0.2F, worldService, vel=vel)
