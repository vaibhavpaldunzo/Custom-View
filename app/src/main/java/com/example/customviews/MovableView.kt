package com.example.customviews


import android.util.Log
import android.view.View

open class MovableView  {

    lateinit var view: View


    fun move(dx: Float, dy: Float){

        showMsg("${view.width} and ${view.height} ")

        view.animate().xBy(dx).yBy(dy).setDuration(0).start()

    }

    private fun showMsg(msg:String) {
        Log.e("MCV"," $msg")
    }
}