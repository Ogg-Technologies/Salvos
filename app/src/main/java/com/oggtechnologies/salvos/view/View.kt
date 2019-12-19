package com.oggtechnologies.salvos.view

import android.graphics.Canvas
import com.oggtechnologies.salvos.model.ModelViewer
import com.oggtechnologies.salvos.model.map.tiles.Tile
import com.oggtechnologies.salvos.model.map.tiles.TileFactory
import com.oggtechnologies.salvos.utilities.Square
import com.oggtechnologies.salvos.utilities.Vector
import com.oggtechnologies.salvos.view.drawers.tiledrawers.GroundDrawer
import com.oggtechnologies.salvos.view.drawers.tiledrawers.TileDrawer
import com.oggtechnologies.salvos.view.drawers.tiledrawers.WallDrawer

class View(private val model: ModelViewer) {

    private val tileDrawers: Map<Class<Tile>, TileDrawer> = hashMapOf(
        TileFactory.createGround().javaClass to GroundDrawer(),
        TileFactory.createWall().javaClass to WallDrawer()
    )

    fun draw(canvas: Canvas) {
        val tileSize = 150f

        for (y in 0..4) {
            for (x in 0..4) {
                val tile = model.tileMap.getTile(x, y)
                val drawer = tileDrawers[tile.javaClass]
                drawer!!.draw(Square(Vector(x.toFloat()*tileSize, y.toFloat()*tileSize), tileSize), canvas)
            }
        }

    }

}