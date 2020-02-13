package com.example.customviews

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnTouchListener {

    var prevTouchX : Float = 0f
    var prevTouchY : Float = 0f

    private var maxWidth : Float = 0f
    private var maxHeight : Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showMsg("Frame Layout start ----> ${frameLayout.width}, ${frameLayout.height}")

        textView.setOnTouchListener(this)

        imageView.setOnTouchListener(this)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        showMsg("Frame Layout start ----> ${frameLayout.width}, ${frameLayout.height}")

        showMsg(v.toString())

        when(event!!.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                prevTouchX = event.rawX
                prevTouchY = event.rawY
            }

            MotionEvent.ACTION_MOVE -> {
                var dx = event.rawX - prevTouchX
                var dy = event.rawY - prevTouchY

                var movableView = MovableView(v!!)

                if (insideScreen(v.x + dx, v.y + dy, v.x + v.width + dx, v.y + v.height + dy))
                    movableView.move(dx, dy)

                prevTouchX = event.rawX
                prevTouchY = event.rawY
            }

            MotionEvent.ACTION_UP -> {

            }
        }

        return true
    }

    fun insideScreen(topLeftX : Float, topLeftY : Float, bottomRightX : Float, bottomRightY : Float) : Boolean {
        showMsg("MAX DIM :- ($maxWidth, $maxHeight)")
        return 0 <= topLeftX && 0 <= topLeftY && bottomRightX <= frameLayout.width && bottomRightY <= frameLayout.height
    }


    private fun showMsg(msg:String) {
        Log.e("MainActivity", msg)
    }

}
