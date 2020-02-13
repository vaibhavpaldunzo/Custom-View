package com.example.customviews

import android.widget.ImageView
import android.widget.TextView

class CustomImageView(imageView: TextView) : MovableView() {
    init {
        view = imageView
    }
}