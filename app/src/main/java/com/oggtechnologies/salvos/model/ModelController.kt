package com.oggtechnologies.salvos.model


interface ModelController {
    fun sendCommand(command: ControllerCommand)
}