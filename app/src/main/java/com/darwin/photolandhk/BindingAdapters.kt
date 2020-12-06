package com.darwin.photolandhk

import android.annotation.SuppressLint
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

@BindingAdapter("imageUrl", "overview", "isAuthorThumbnail", requireAll = false)
fun bindImage(imgView: ImageView, imgUrl: String?, isHomeOverview: Boolean = false, isAuthorThumbnail: Boolean = false) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        val reqOptions =
            if (isHomeOverview)
                RequestOptions().fitCenter().override(320, 180).error(R.drawable.ic_broken_image)
            else if (isAuthorThumbnail)
                RequestOptions().fitCenter().override(50, 50).error(R.drawable.ic_broken_image)
            else
                RequestOptions().fitCenter().override(640, 360).error(R.drawable.ic_broken_image)
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
        is HomeNewsOverviewAdapter -> (recyclerView.adapter as HomeNewsOverviewAdapter).submitList(
            data
        )
        is HomeReportOverviewAdapter -> (recyclerView.adapter as HomeReportOverviewAdapter).submitList(
            data
        )
        is HomeDiscussionOverviewAdapter -> (recyclerView.adapter as HomeDiscussionOverviewAdapter).submitList(
            data
        )
        is ReportOverviewAdapter -> (recyclerView.adapter as ReportOverviewAdapter).submitList(data)
        is NewsOverviewAdapter -> (recyclerView.adapter as NewsOverviewAdapter).submitList(data)
        else -> null
    }
}

@SuppressLint("SetJavaScriptEnabled")
@BindingAdapter("postContent")
fun bindPostContent(webView: WebView, data: String) {
    webView.settings.javaScriptEnabled = true
    webView.settings.loadWithOverviewMode = true
//    webView.settings.minimumFontSize = 15
    var styles = "<!DOCTYPE html><html><head>"
    for (link in CSSs){
        styles += "<link href=\"${link}\" rel=\"stylesheet\">"
    }
    webView.loadDataWithBaseURL(null, styles+"</head><body>"+data+"</body></html>","text/HTML", "UTF-8", null)
}

@BindingAdapter("text")
fun bindText(textView: TextView, data: String) {
    textView.text = HtmlCompat.fromHtml(data, HtmlCompat.FROM_HTML_MODE_LEGACY)
}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: ApiStatus?) {
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

/*
 * List of CSS styles
 */
private val CSSs = listOf(
    "https://photolandhk.com/wp-includes/css/dashicons.min.css?ver=5.5.3",
    "https://photolandhk.com/wp-content/themes/jannah/assets/css/plugins/buddypress.min.css?ver=5.0.9",
    "https://photolandhk.com/wp-includes/css/dist/block-library/style.min.css?ver=5.5.3",  // listing in on line
    "https://photolandhk.com/wp-includes/css/dist/block-library/theme.min.css?ver=5.5.3",
    "https://photolandhk.com/wp-content/plugins/buddypress/bp-members/css/blocks/member.min.css?ver=6.3.0",
    "https://photolandhk.com/wp-content/plugins/woocommerce/packages/woocommerce-blocks/build/vendors-style.css?ver=3.6.0",
    "https://photolandhk.com/wp-content/plugins/woocommerce/packages/woocommerce-blocks/build/style.css?ver=3.6.0",
    "https://photolandhk.com/wp-content/plugins/block-options/build/style.build.css?ver=1.29.3",
    "https://photolandhk.com/wp-content/themes/jannah/assets/css/plugins/woocommerce.min.css?ver=5.0.9",
    "https://photolandhk.com/wp-content/plugins/aps-products/css/aps-styles.css?ver=2.6",
    "https://photolandhk.com/wp-content/plugins/aps-products/css/imageviewer.css?ver=2.6",
    "https://photolandhk.com/wp-content/plugins/aps-products/css/nivo-lightbox.css?ver=2.6",
    "https://photolandhk.com/wp-content/plugins/aps-products/css/owl-carousel.css?ver=2.6",
    "https://photolandhk.com/wp-content/themes/jannah/assets/css/base.min.css?ver=5.0.9",
    "https://photolandhk.com/wp-content/plugins/jetpack/css/jetpack.css?ver=9.1",
    "https://photolandhk.com/wp-content/themes/jannah/assets/css/style.min.css",
    "https://photolandhk.com/wp-content/themes/jannah/assets/css/single.min.css",
    "https://photolandhk.com/wp-content/themes/jannah/assets/css/plugins/shortcodes.min.css",
    "https://photolandhk.com/wp-content/themes/jannah/assets/css/widgets.min.css",
    "https://photolandhk.com/wp-content/themes/jannah/assets/css/helpers.min.css",
    "https://photolandhk.com/wp-content/themes/jannah/assets/ilightbox/dark-skin/skin.css",
    "https://photolandhk.com/wp-content/themes/jannah/assets/css/fontawesome.css",
    "https://photolandhk.com/wp-content/themes/jannah/assets/css/print.css?ver=5.0.9",
    "https://www.youtube.com/s/player/408be03a/www-player-webp.css",
    "https://fonts.googleapis.com/css?family=Noto+Sans+TC:300,regular%7CNoto+Sans+TC:700,regular%7CNoto+Sans+TC:600,regular&subset=latin,latin,latin&display=swap",
    "https://www.youtube.com/s/player/408be03a/www-player-webp.css",
//    "https://www.youtube.com/yts/cssbin/www-subscribe-embed-webp-vflHrs6dn.css",
//    "https://www.youtube.com/yts/cssbin/www-subscribe-embed-card-webp-vfljgQue1.css",
    "https://www.facebook.com/rsrc.php/v3/y3/l/1,cross/khLib2ZYeaz.css?_nc_x=Ij3Wp8lg5Kz"
)

private val JSs = listOf(
    "https://photolandhk.com/wp-content/plugins/aps-products/js/aps-main-script-min.js?ver=2.6",
    "https://photolandhk.com/wp-content/plugins/aps-products/js/imageviewer.min.js?ver=2.6",
    "https://photolandhk.com/wp-content/plugins/aps-products/js/nivo-lightbox.min.js?ver=2.6",
    "https://photolandhk.com/wp-content/plugins/aps-products/js/owl.carousel.min.js?ver=2.6",
    "https://photolandhk.com/wp-content/plugins/ultimate-blocks/src/blocks/content-toggle/front.build.js?ver=2.4.1",
    "https://photolandhk.com/wp-content/themes/jannah/assets/ilightbox/lightbox.js?ver=5.0.9",
    "https://photolandhk.com/wp-content/themes/jannah/assets/js/br-news.js?ver=5.0.9",
    "https://photolandhk.com/wp-content/themes/jannah/assets/js/desktop.min.js?ver=5.0.9",
    "https://photolandhk.com/wp-content/themes/jannah/assets/js/live-search.js?ver=5.0.9",
    "https://photolandhk.com/wp-content/themes/jannah/assets/js/scripts.min.js?ver=5.0.9",
    "https://photolandhk.com/wp-content/themes/jannah/assets/js/shortcodes.js?ver=5.0.9",
    "https://photolandhk.com/wp-content/themes/jannah/assets/js/single.min.js?ver=5.0.9",
    "https://photolandhk.com/wp-content/themes/jannah/assets/js/sliders.min.js?ver=5.0.9",
    "https://photolandhk.com/wp-includes/js/wp-embed.min.js?ver=5.5.3",
    "https://photolandhk.com/wp-includes/js/wp-emoji-release.min.js?ver=5.5.3",
    "https://apis.google.com/js/platform.js?ver=5.5.3"
)