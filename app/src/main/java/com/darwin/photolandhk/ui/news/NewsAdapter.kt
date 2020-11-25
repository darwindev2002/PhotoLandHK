package com.darwin.photolandhk.ui.news

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.darwin.photolandhk.R
import com.darwin.photolandhk.depreciated.PostProcessing
import com.darwin.photolandhk.ui.post.PostFragment
import org.json.JSONArray

class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var title = ""
    private var id = 0
    private var current_shown_cnt = 30
    private var total_count = 30
    private lateinit var newsList: JSONArray

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mImageView: ImageView = itemView.findViewById(R.id.news_card_image)
        val mTitle: TextView = itemView.findViewById(R.id.news_card_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_overview_card_big, parent, false)

        title = view.context.getString(R.string.title_newspaper)
        id = PostProcessing.category_map[title]?.id as Int
        total_count = PostProcessing.category_map[title]?.count as Int
        current_shown_cnt = if (current_shown_cnt > total_count) total_count else current_shown_cnt
        newsList = PostProcessing.getPostOverview(id, count=current_shown_cnt)

        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return current_shown_cnt
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = newsList.getJSONObject(position)

        Glide.with(holder.mImageView.context).load(currentItem.optString("featured_image_src")).into(holder.mImageView)
        holder.mTitle.text = currentItem.getJSONObject("title").optString("rendered")
        val post_id: Int = currentItem.optInt("id")
        holder.itemView.setOnClickListener {
            it.context.startActivity(Intent(it.context, PostFragment::class.java).putExtra("post_id", post_id))
        }
    }

}