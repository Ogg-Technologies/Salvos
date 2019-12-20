package com.oggtechnologies.salvos.view

import android.graphics.Canvas
import com.oggtechnologies.salvos.model.ModelViewer
import com.oggtechnologies.salvos.model.map.tiles.Tile
import com.oggtechnologies.salvos.model.map.tiles.TileFactory
import com.oggtechnologies.salvos.utilities.Square
import com.oggtechnologies.salvos.utilities.Vector
import com.oggtechnologies.salvos.view.drawers.entitydrawers.PlayerDrawer
import com.oggtechnologies.salvos.view.drawers.tiledrawers.GroundDrawer
import com.oggtechnologies.salvos.view.drawers.tiledrawers.TileDrawer
import com.oggtechnologies.salvos.view.drawers.tiledrawers.WallDrawer

class View(private val model: ModelViewer) {
    val tileSize = 150f

    private val tileDrawers: Map<Class<Tile>, TileDrawer> = hashMapOf(
        TileFactory.createGround().javaClass to GroundDrawer(),
        TileFactory.createWall().javaClass to WallDrawer()
    )

    private val playerDrawer = PlayerDrawer()

    fun draw(canvas: Canvas, screenSize: Vector) {
        drawTiles(canvas)
        drawPlayer(canvas, screenSize)
    }

    private fun drawPlayer(canvas: Canvas, screenSize: Vector) {
        val player = model.player
        println(screenSize)
        val square = Square.byCenter(screenSize/2F, tileSize)
        playerDrawer.draw(square, canvas)
    }

    private fun drawTiles(canvas: Canvas) {
        for (y in 0 until model.tileMap.mapSize) {
            for (x in 0 until model.tileMap.mapSize) {
                val tile = model.tileMap.getTile(x, y)
                val drawer = tileDrawers[tile.javaClass]
                drawer!!.draw(
                    Square(
                        Vector(x.toFloat() * tileSize, y.toFloat() * tileSize),
                        tileSize
                    ), canvas
                )
            }
        }
    }

}