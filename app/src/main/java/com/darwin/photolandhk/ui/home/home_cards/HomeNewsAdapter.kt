package com.darwin.photolandhk.ui.home.home_cards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.darwin.photolandhk.R
import com.darwin.photolandhk.ui.posts.PostFragment

class HomeNewsAdapter() : RecyclerView.Adapter<HomeNewsAdapter.HomeNewsViewHolder>() {

    private val mExampleList = ExampleList.theList

    class HomeNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mImageView: ImageView = itemView.findViewById(R.id.news_recyclerview_imageView)
        val mTitle: TextView = itemView.findViewById(R.id.news_recyclerview_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_news_item, parent, false)
        view.setOnClickListener{
//            (activity as MainActivity).loadPage(parent.parent.parent.parent, R.id.nav_news)
        }
        return HomeNewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mExampleList.size
    }

    override fun onBindViewHolder(holder: HomeNewsViewHolder, position: Int) {
        val currentItem = mExampleList[position]

//        GlideApp.with(holder.mImageView.context)
//            .load(currentItem.imageURL)
//            .into(holder.mImageView)
        holder.mImageView.setImageResource(currentItem.image)
        holder.mTitle.text = currentItem.title
        holder.itemView.setOnClickListener {
                val activity: AppCompatActivity = it.context as AppCompatActivity
                val postFragment: PostFragment = PostFragment()
                activity.supportFragmentManager.beginTransaction().replace(R.id.fragment_container, postFragment).commit()
        }

//        holder.mDate.text = currentItem.date
    }

}