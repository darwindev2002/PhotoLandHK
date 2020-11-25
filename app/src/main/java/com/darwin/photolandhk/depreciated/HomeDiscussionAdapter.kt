package com.darwin.photolandhk.depreciated

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.darwin.photolandhk.R
import com.darwin.photolandhk.depreciated.PostProcessing.ITEM_COUNT
import com.darwin.photolandhk.depreciated.PostProcessing.category_map
import com.darwin.photolandhk.ui.post.PostFragment
import org.json.JSONArray

class HomeDiscussionAdapter() : RecyclerView.Adapter<HomeDiscussionAdapter.DiscussionViewHolder>() {

    private var title = ""
    private var id = 0
    private var count = ITEM_COUNT
    private lateinit var discussionOverviews: JSONArray

    class DiscussionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mImageView: ImageView = itemView.findViewById(R.id.discussion_recyclerview_imageView)
        val mTitle: TextView = itemView.findViewById(R.id.discussion_recyclerview_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscussionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_discussion_item, parent, false)
        title = view.context.getString(R.string.title_discussion)
        id = category_map[title]?.id as Int
        val real_cnt = category_map[title]?.count as Int
        count = if (count > real_cnt) real_cnt else count
        discussionOverviews = PostProcessing.getPostOverview(id, count=count)
        return DiscussionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return count
    }

    override fun onBindViewHolder(holder: DiscussionViewHolder, position: Int) {
        val currentItem = discussionOverviews.getJSONObject(position)

        Glide.with(holder.mImageView.context).load(currentItem.optString("featured_image_src")).into(holder.mImageView)
        holder.mTitle.text = currentItem.getJSONObject("title").optString("rendered")
        val post_id: Int = currentItem.optInt("id")
        holder.itemView.setOnClickListener {
            it.context.startActivity(Intent(it.context, PostFragment::class.java).putExtra("post_id", post_id))
        }
    }

}