package com.oggtechnologies.salvos.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.provider.CalendarContract
import com.oggtechnologies.salvos.model.ModelViewer
import com.oggtechnologies.salvos.model.map.tiles.Tile
import com.oggtechnologies.salvos.model.map.tiles.TileFactory
import com.oggtechnologies.salvos.utilities.SharedPaint
import com.oggtechnologies.salvos.utilities.shapes.implementations.Square
import com.oggtechnologies.salvos.utilities.Vector
import com.oggtechnologies.salvos.utilities.shapes.implementations.Circle
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
        drawTiles(player.pos, canvas)
        drawPlayer(canvas)
        drawProjectiles(canvas)


        val paint = Paint()
        paint.color = Color.WHITE
        paint.textSize = 40F
        canvas.drawText("X: ${model.player.pos.x} Y: ${model.player.pos.y}", 60F, 100F, paint)
    }

    private fun drawProjectiles(canvas: Canvas) {
        SharedPaint.color = Color.YELLOW
        for (p in model.projectiles) {
            val pos = worldToScreenPos(p.pos)
            val size = p.bounds.size*tileSize/2
            val left = pos.x-size
            val top = pos.y-size
            val right = pos.x+size
            val bottom = pos.y+size
            canvas.drawRect(left, top, right, bottom, SharedPaint)
        }
    }

    private fun drawPlayer(canvas: Canvas) {
        val square = Square.byCenter(screenSize/2F, tileSize*model.player.bounds.size)
        playerDrawer.draw(square, canvas)
    }

    private fun drawTiles(playerPos: Vector, canvas: Canvas) {
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

    private fun worldToScreenPos(worldPos: Vector): Vector {
        val originTileScreenPos = calculateOriginTileScreenPos(model.player.pos, screenSize, tileSize)
        val screenPos = originTileScreenPos + worldPos*tileSize
        return screenPos
    }

    private fun calculateOriginTileScreenPos(playerPos: Vector, screenSize: Vector, tileSize: Float): Vector {
        return (screenSize/2F) - (playerPos*tileSize)
    }

}