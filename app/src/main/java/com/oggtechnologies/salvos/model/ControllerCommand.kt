package com.oggtechnologies.salvos.model

import com.oggtechnologies.salvos.utilities.Vector

sealed class ControllerCommand

class Move(val dir: Vector) : ControllerCommand()

class StartChargeGun(val dir: Vector) : ControllerCommand()

class ShootGun(val dir: Vector) : ControllerCommand()

class AimGun(val dir: Vector) : ControllerCommand()
