package com.darwin.photolandhk.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.darwin.photolandhk.R

class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val mExampleList = ExampleNewsList.theList

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mImageView: ImageView = itemView.findViewById(R.id.news_card_image)
        val mTitle: TextView = itemView.findViewById(R.id.news_card_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_card, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mExampleList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = mExampleList[position]

//        GlideApp.with(holder.mImageView.context)
//            .load(currentItem.imageURL)
//            .into(holder.mImageView)
        holder.mImageView.setImageResource(currentItem.image)
        holder.mTitle.text = currentItem.title
//        holder.mDate.text = currentItem.date
    }

}