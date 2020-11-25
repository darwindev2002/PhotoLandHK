package com.darwin.photolandhk

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.darwin.photolandhk.network.ApiStatus
import com.darwin.photolandhk.posts.PostContent
import com.darwin.photolandhk.ui.PostBigOverviewAdapter
import com.darwin.photolandhk.ui.home.home_cards.HomeDiscussionOverviewAdapter
import com.darwin.photolandhk.ui.home.home_cards.HomeNewsOverviewAdapter
import com.darwin.photolandhk.ui.home.home_cards.HomeReportOverviewAdapter

@BindingAdapter("imageUrl","overview", requireAll = false)
fun bindImage(imgView: ImageView, imgUrl: String?, isHomeOverview: Boolean = false) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        val reqOptions = RequestOptions().fitCenter().override(390,220).placeholder(R.drawable.loading_img).error(R.drawable.ic_broken_image)
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
        is PostBigOverviewAdapter -> (recyclerView.adapter as PostBigOverviewAdapter).submitList(data)
        else -> null
    }
//    if (recyclerView.adapter is HomeNewsOverviewAdapter) (recyclerView.adapter)
//    val adapter = recyclerView.adapter as PostBigOverviewAdapter
//    adapter.submitList(data)
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