package com.mindinventory.overlapimagegalleyviewsample.utils

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.Toast
import java.util.*


fun getRandomNumber(min: Int = 0, max: Int = 50): Int {
    return Random().nextInt(max - min + 1) + min
}

fun getRandomColor(): Int {
    val rnd = Random()
    return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
}

fun convertPixelsToDp(px: Int, context: Context): Int {
    return px / (context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
}

fun convertDpToPixel(dp: Int, context: Context): Int {
    val resources = context.resources
    val metrics = resources.displayMetrics
    return dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
}

fun getScreenWidth(context: Context): Int {
    var width = 0
    val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = wm.defaultDisplay
    val size = Point()
    display.getSize(size)
    width = size.x
    return width
}

fun toast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

val imageURLs = arrayOf("https://www.ienglishstatus.com/wp-content/uploads/2018/04/cute-profile-pics-for-whatsapp-images.png",
        "https://106haz2kmt3j3ngud0342oo2-wpengine.netdna-ssl.com/wp-content/uploads/2017/12/Commercial-Architects_6_Portland_Portland-Airport-Concourse-E-Extension.jpg",
        "http://www.gombrel.com/i/2016/09/amazing-university-building-design-and-architecture-cool-curved-lake-builsing-idea-awesome-large-with-luxury-white-color-onion-dome-brighton-creative-sphere-volume-decoration-mountain.jpg")
