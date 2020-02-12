package com.example.customviews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.FrameMetrics
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.annotation.Nullable

class MyCustomView : View {

    var mTopLeftX: Float
    var mTopLeftY: Float

    var centerX: Float
    var centerY: Float

    var mHeight: Float
    var mWidth: Float

    var mPaint: Paint
    var mRect: RectF

    var prevTouchX: Float? = null
    var prevTouchY: Float? = null
    var onBox: Boolean = false

    init {
        mTopLeftX = 0.toFloat()
        mTopLeftY = 0.toFloat()

        mHeight = 250.toFloat()
        mWidth = 250.toFloat()

        centerX = mWidth / 2
        centerY = mHeight / 2

        mRect = RectF()

        mPaint = Paint()
        mPaint.setColor(Color.MAGENTA)
    }

    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {

    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        setViewBounds()

        canvas?.drawRect(mRect, mPaint)
    }

    private fun setViewBounds() {
        mRect.left = centerX - (mWidth / 2)
        mRect.top = centerY - (mHeight / 2)
        mRect.right = centerX + (mWidth / 2)
        mRect.bottom = centerY + (mHeight / 2)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var eventAction = event?.action

        var touchX = event?.getX()
        var touchY = event?.getY()

        when (eventAction) {
            MotionEvent.ACTION_DOWN -> {
                showToast("ACTION DOWN")
                onBox = insideBox(touchX!!, touchY!!)

                prevTouchX = touchX
                prevTouchY = touchY
            }

            MotionEvent.ACTION_MOVE -> {
                showToast("ACTION MOVE")
                if (onBox) {
                    var dx:Float = touchX!! - prevTouchX!!
                    var dy:Float = touchY!! - prevTouchY!!

                    centerX += dx
                    centerY += dy

                    mTopLeftX = centerX - (mWidth / 2)
                    mTopLeftY = centerY - (mHeight / 2)

                    prevTouchX = touchX
                    prevTouchY = touchY

                    invalidate()
                }
            }

            MotionEvent.ACTION_UP -> {
                showToast("ACTION UP")
            }
        }

        return true
    }

    private fun insideBox(x:Float, y:Float) : Boolean {
        return (mTopLeftX <= x && x <= mTopLeftX + mWidth) && (mTopLeftY <= y && y <= mTopLeftY + mHeight)
    }

    private fun showToast(msg:String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

}