package com.darwin.photolandhk.ui.home.home_cards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.darwin.photolandhk.R

class HomeDiscussionAdapter() : RecyclerView.Adapter<HomeDiscussionAdapter.DiscussionViewHolder>() {

    private val mExampleList = ExampleList.theList

    class DiscussionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mImageView: ImageView = itemView.findViewById(R.id.discussion_recyclerview_imageView)
        val mTitle: TextView = itemView.findViewById(R.id.discussion_recyclerview_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeDiscussionAdapter.DiscussionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_discussion_item, parent, false)
        return DiscussionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mExampleList.size
    }

    override fun onBindViewHolder(holder: DiscussionViewHolder, position: Int) {
        val currentItem = mExampleList[position]

//        GlideApp.with(holder.mImageView.context)
//            .load(currentItem.imageURL)
//            .into(holder.mImageView)
        holder.mImageView.setImageResource(currentItem.image)
        holder.mTitle.text = currentItem.title
//        holder.mDate.text = currentItem.date
    }

}