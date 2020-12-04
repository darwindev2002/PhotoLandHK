package com.darwin.photolandhk

import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.darwin.photolandhk.network.ApiStatus
import com.darwin.photolandhk.posts.PostContent
import com.darwin.photolandhk.ui.home.home_cards.HomeDiscussionOverviewAdapter
import com.darwin.photolandhk.ui.home.home_cards.HomeNewsOverviewAdapter
import com.darwin.photolandhk.ui.home.home_cards.HomeReportOverviewAdapter
import com.darwin.photolandhk.ui.news.NewsOverviewAdapter
import com.darwin.photolandhk.ui.report.ReportOverviewAdapter

@BindingAdapter("imageUrl","overview", requireAll = false)
fun bindImage(imgView: ImageView, imgUrl: String?, isHomeOverview: Boolean = false) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        val reqOptions = if (isHomeOverview) RequestOptions().fitCenter().override(320,180).error(R.drawable.ic_broken_image)
                               else RequestOptions().fitCenter().override(640,360).error(R.drawable.ic_broken_image)
        Glide.with(imgView.context)
            .asBitmap()
            .apply(reqOptions)
            .load(imgUri)
            .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<PostContent>?) {
    when (recyclerView.adapter) {
        is HomeNewsOverviewAdapter -> (recyclerView.adapter as HomeNewsOverviewAdapter).submitList(data)
        is HomeReportOverviewAdapter -> (recyclerView.adapter as HomeReportOverviewAdapter).submitList(data)
        is HomeDiscussionOverviewAdapter -> (recyclerView.adapter as HomeDiscussionOverviewAdapter).submitList(data)
        is ReportOverviewAdapter -> (recyclerView.adapter as ReportOverviewAdapter).submitList(data)
        is NewsOverviewAdapter -> (recyclerView.adapter as NewsOverviewAdapter).submitList(data)
        else -> null
    }
}

@BindingAdapter("postContent")
fun bindPostContent(webView: WebView, data: String) {
//    textView.text = HtmlCompat.fromHtml(data,HtmlCompat.FROM_HTML_MODE_COMPACT)
//    textView.setInitialScale(0)
    webView.settings.javaScriptEnabled = true
    webView.settings.loadWithOverviewMode = true
//    textView.settings.useWideViewPort = false
    webView.loadDataWithBaseURL(null,
        "<style>img{display: block;height: auto;max-width: 100%;}</style>" +
            "<style>iframe{display: block;height: auto;max-width: 100%;}</style>" +
            "<style>video{display: block;height: auto;max-width: 100%;}</style>" + data,
        "text/HTML","UTF-8",null)
    println(data)
}

@BindingAdapter("text")
fun bindText(textView: TextView, data: String) {
    textView.text = HtmlCompat.fromHtml(data, HtmlCompat.FROM_HTML_MODE_LEGACY)
}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: ApiStatus?){
    when (status) {
        ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_img)
        }
        ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}