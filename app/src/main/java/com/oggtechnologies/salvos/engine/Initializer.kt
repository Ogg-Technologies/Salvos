package com.example.salvos.engine

import android.content.Context
import android.content.res.Resources


internal object Initializer {
    private var resources: Resources? = null
    fun setUp(res: Resources?) {
        resources = res
        ImageHandler.initialize(res)
    }

    fun firstFrame(context: Context?) {}
}
