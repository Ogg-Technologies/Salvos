package com.example.salvos.engine

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import java.util.*


object ImageHandler {
    private const val RECYCLE_CACHE_MEMORY_THRESHOLD = 90000000
    private val scaledBitmaps: MutableMap<Long, Bitmap?> =
        LinkedHashMap()
    private var res // Resources reference
            : Resources? = null
    private var matrix // Used for scaling resized bitmaps
            : Matrix? = null
    private var options // For getting the width and height from a resource
            : BitmapFactory.Options? = null

    fun initialize(resources: Resources?) {
        res = resources
        matrix = Matrix()
        options = BitmapFactory.Options()
        options!!.inJustDecodeBounds = true
    }

    /**
     * Fetches a reference to the given bitmap from cache if it exists,
     * else it loads it and puts it in the list of loaded bitmaps
     * @param bitmapID      The id of the bitmap to be returned
     * @param pixelWidth    The width of the bitmap
     * @return              A reference to the bitmap, ready to be drawn
     */
    fun getBitmap(bitmapID: Int, pixelWidth: Int): Bitmap? {
        val key = bitmapID.toLong() * pixelWidth
        // If the bitmap exists in the correct scale return it
        if (scaledBitmaps.containsKey(key)) {
            return scaledBitmaps[key]
        }
        // Recycle cache if the memory is low
        recycleCache()
        // If the bitmap is in the wrong scale, create a new scaled one
        val scaledBitmap = createResizedBitmap(bitmapID, pixelWidth.toFloat())
        scaledBitmaps[key] = scaledBitmap
        return scaledBitmap
    }

    /**
     * Decodes, scales and saves a new bitmap from the given parameters
     * @param bitmapID      The id of the bitmap to be loaded
     * @param pixelWidth    The width it should be scaled at
     * @return              The new bitmap
     */
    private fun createResizedBitmap(bitmapID: Int, pixelWidth: Float): Bitmap {
        val decoded = BitmapFactory.decodeResource(res, bitmapID)
        val width = decoded.width
        val height = decoded.height
        val scale = pixelWidth / width
        matrix!!.setScale(scale, scale)
        return Bitmap.createBitmap(decoded, 0, 0, width, height, matrix, false)
    }

    /**
     * Continuously removes the first bitmap in the list and runs the garbage
     * collector until the available memory is less than a given threshold
     */
    private fun recycleCache() {
        while (availableMemory < RECYCLE_CACHE_MEMORY_THRESHOLD) {
            if (scaledBitmaps.size == 0) {
                return
            }
            val first = scaledBitmaps.keys.iterator().next()
            scaledBitmaps.remove(first)
            println("Removing bitmaps from memory...")
        }
    }

    /**
     * Runs the garbage collector and then calculates how much RAM is left
     * @return      The amount of free RAM left on the device
     */
    private val availableMemory: Long
        private get() {
            Runtime.getRuntime().gc()
            return Runtime.getRuntime().maxMemory() -
                    Runtime.getRuntime().totalMemory() +
                    Runtime.getRuntime().freeMemory()
        }

    /**
     * Loads the given bitmaps to RAM. Call once during startup
     * @param bitmapIDs     Array of bitmapIDs to be loaded
     * @param pixelWidths   The width of the image it corresponds to
     */
    fun loadBitmaps(bitmapIDs: IntArray, pixelWidths: IntArray) {
        require(bitmapIDs.size == pixelWidths.size) { "Different array lengths are not allowed" }
        for (i in bitmapIDs.indices) {
            val scaledBitmap =
                createResizedBitmap(bitmapIDs[i], pixelWidths[i].toFloat())
            scaledBitmaps[bitmapIDs[i].toLong() * pixelWidths[i]] = scaledBitmap
        }
    }

    /**
     * Takes in a bitmap with a certain width and outputs
     * the height it should have to keep the aspect ratio
     * @param bitmapID  The id of the bitmap
     * @param width     The width for that bitmap
     * @return          The height for that bitmap to keep the original ratio
     */
    fun getHeightFromWidth(bitmapID: Int, width: Int): Int {
        BitmapFactory.decodeResource(res, bitmapID, options)
        val scale = width.toFloat() / options!!.outWidth
        return (options!!.outHeight * scale).toInt()
    }

    /**
     * Takes in a bitmap with a certain height and outputs
     * the width it should have to keep the aspect ratio
     * @param bitmapID  The id of the bitmap
     * @param height    The height for that bitmap
     * @return          The width for that bitmap to keep the original ratio
     */
    fun getWidthFromHeight(bitmapID: Int, height: Int): Int {
        BitmapFactory.decodeResource(res, bitmapID, options)
        val scale = height.toFloat() / options!!.outHeight
        return (options!!.outWidth * scale).toInt()
    }

    /**
     * Returns the width of the original, unscaled image
     * @param bitmapID      The id of the bitmap
     * @return              The bitmap's original width
     */
    fun getOriginalWidth(bitmapID: Int): Int {
        BitmapFactory.decodeResource(res, bitmapID, options)
        return options!!.outWidth
    }

    /**
     * Returns the height of the original, unscaled image
     * @param bitmapID      The id of the bitmap
     * @return              The bitmap's original height
     */
    fun getOriginalHeight(bitmapID: Int): Int {
        BitmapFactory.decodeResource(res, bitmapID, options)
        return options!!.outHeight
    }
}

