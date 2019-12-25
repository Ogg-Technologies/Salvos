package com.oggtechnologies.salvos.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.oggtechnologies.salvos.model.ModelViewer
import com.oggtechnologies.salvos.model.map.tiles.Tile
import com.oggtechnologies.salvos.model.map.tiles.TileFactory
import com.oggtechnologies.salvos.utilities.shapes.implementations.Square
import com.oggtechnologies.salvos.utilities.Vector
import com.oggtechnologies.salvos.view.drawers.entitydrawers.PlayerDrawer
import com.oggtechnologies.salvos.view.drawers.tiledrawers.GroundDrawer
import com.oggtechnologies.salvos.view.drawers.tiledrawers.TileDrawer
import com.oggtechnologies.salvos.view.drawers.tiledrawers.WallDrawer

class View(private val model: ModelViewer, private val screenSize: Vector) {
    private val tileSize = 150f

    private val tileDrawers: Map<Class<Tile>, TileDrawer> = hashMapOf(
        TileFactory.createGround().javaClass to GroundDrawer(),
        TileFactory.createWall().javaClass to WallDrawer()
    )

    private val playerDrawer = PlayerDrawer()

    fun draw(canvas: Canvas) {
        val player = model.player
        drawTiles(player.pos, canvas, screenSize)
        drawPlayer(canvas, screenSize)


        val paint = Paint()
        paint.color = Color.WHITE
        paint.textSize = 40F
        canvas.drawText("X: ${model.player.pos.x} Y: ${model.player.pos.y}", 60F, 100F, paint)
    }

    private fun drawPlayer(canvas: Canvas, screenSize: Vector) {
        println(screenSize)
        val square = Square.byCenter(screenSize/2F, tileSize)
        playerDrawer.draw(square, canvas)
    }

    private fun drawTiles(playerPos: Vector, canvas: Canvas, screenSize: Vector) {
        val originTileScreenPos = calculateOriginTileScreenPos(playerPos, screenSize, tileSize)
        for (tileY in 0 until model.tileMap.mapSize) {
            for (tileX in 0 until model.tileMap.mapSize) {
                val tile = model.tileMap.getTile(tileX, tileY)
                val drawer = tileDrawers[tile.javaClass]
                val tilePos = Vector(tileX.toFloat(), tileY.toFloat())
                val screenPos = originTileScreenPos + tilePos*tileSize
                drawer!!.draw(
                    Square(
                        screenPos,
                        tileSize
                    ), canvas
                )
            }
        }
    }

    private fun calculateOriginTileScreenPos(playerPos: Vector, screenSize: Vector, tileSize: Float): Vector {
        return (screenSize/2F) - (playerPos*tileSize)
    }

}