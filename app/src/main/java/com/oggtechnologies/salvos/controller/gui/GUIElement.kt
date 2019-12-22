package com.oggtechnologies.salvos.controller.gui

import com.oggtechnologies.salvos.utilities.Vector

interface GUIElement {
    fun touchDown(screenPos: Vector, fingerID: Int)

    fun touchRelease(screenPos: Vector, fingerID: Int)

    fun touchMove(screenPos: Vector, fingerID: Int)

    val pos: Vector
}