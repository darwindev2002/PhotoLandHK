package com.darwin.photolandhk.posts

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.text.Html
import java.net.HttpURLConnection
import java.net.URL

object ImageGetter : Html.ImageGetter {
    val width = Resources.getSystem().displayMetrics.widthPixels

    override fun getDrawable(src: String): Drawable {
        lateinit var drawable: Drawable
        val urlConnection = URL(src).openConnection() as HttpURLConnection
        try {
            val inputStream = urlConnection.getInputStream()
            drawable = Drawable.createFromStream(inputStream, "src")
            drawable.setBounds(0, 0, drawable.intrinsicWidth*width/drawable.intrinsicWidth-10, drawable.intrinsicHeight*width/drawable.intrinsicWidth-10)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return drawable
    }
}