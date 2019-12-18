package com.oggtechnologies.salvos

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var application: Application? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = Application(this)
        setContentView(application.view)
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

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return application!!.onTouchEvent(event)
    }
}
