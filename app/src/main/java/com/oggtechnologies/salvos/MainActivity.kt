package com.oggtechnologies.salvos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var application: Application? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = Application(this)
        setContentView(application)
        this.application = application
    }

    override fun onResume() {
        super.onResume()
        application!!.resume()
    }

    override fun onPause() {
        super.onPause()
        application!!.pause()
    }

}
