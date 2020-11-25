package com.darwin.photolandhk.depreciated

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.StrictMode
import android.text.Html
import android.widget.ImageView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.darwin.photolandhk.R
import java.net.HttpURLConnection
import java.net.URL

object ImageGetter : Html.ImageGetter {
    val width = Resources.getSystem().displayMetrics.widthPixels

    override fun getDrawable(src: String): Drawable {
        lateinit var drawable: Drawable
        val policy: StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val urlConnection = URL(src).openConnection() as HttpURLConnection
        try {
            urlConnection.connect()
            drawable = Drawable.createFromStream(urlConnection.inputStream, null)
            drawable.setBounds(0, 0, drawable.intrinsicWidth*width/drawable.intrinsicWidth-10, drawable.intrinsicHeight*width/drawable.intrinsicWidth-10)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return drawable
    }
}

fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        val reqOptions = RequestOptions()
            .fitCenter()
            .override(390,220)
        Glide.with(imgView.context)
            .asBitmap()
            .apply(reqOptions)
            .load(imgUri)
            .apply(
                RequestOptions()
                .error(R.drawable.ic_broken_image))
            .placeholder(R.drawable.loading_img)
            .into(imgView)
    }
}