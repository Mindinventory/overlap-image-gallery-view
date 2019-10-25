package com.mindinventory.overlapimagegalleyviewsample.utils

import android.content.Context
import android.widget.Toast

fun toast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

val imageURLs = arrayOf("https://randomuser.me/api/portraits/men/94.jpg",
        "https://randomuser.me/api/portraits/men/22.jpg",
        "https://randomuser.me/api/portraits/men/46.jpg")
