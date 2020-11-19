package com.darwin.photolandhk.ui.home.home_cards

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.darwin.photolandhk.R
import com.darwin.photolandhk.posts.PostProcessing.ITEM_COUNT
import com.darwin.photolandhk.posts.PostProcessing.category_map
import com.darwin.photolandhk.posts.PostProcessing.getPostOverview
import com.darwin.photolandhk.ui.post_fragment_pkg.PostActivity
import org.json.JSONArray

class HomeNewsAdapter() : RecyclerView.Adapter<HomeNewsAdapter.HomeNewsViewHolder>() {

    private var title = ""
    private var id = 0
    private var count = ITEM_COUNT
    private lateinit var newsOverviews: JSONArray

    class HomeNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mImageView: ImageView = itemView.findViewById(R.id.news_recyclerview_imageView)
        val mTitle: TextView = itemView.findViewById(R.id.news_recyclerview_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_news_item, parent, false)
        title = view.context.getString(R.string.title_newspaper)
        id = category_map[title]?.id as Int
        val real_cnt = category_map[title]?.count as Int
        count = if (count > real_cnt) real_cnt else count
        newsOverviews = getPostOverview(id)
        return HomeNewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return count
    }

    override fun onBindViewHolder(holder: HomeNewsViewHolder, position: Int) {
        val currentItem = newsOverviews.getJSONObject(position)

        Glide.with(holder.mImageView.context).load(currentItem.optString("featured_image_src")).into(holder.mImageView)
        holder.mTitle.text = currentItem.getJSONObject("title").optString("rendered")
        val post_id: Int = currentItem.optInt("id")
        holder.itemView.setOnClickListener {
            it.context.startActivity(Intent(it.context, PostActivity::class.java).putExtra("post_id", post_id))
        }
    }

}