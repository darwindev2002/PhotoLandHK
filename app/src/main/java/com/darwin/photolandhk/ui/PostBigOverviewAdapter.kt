package com.darwin.photolandhk.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.darwin.photolandhk.databinding.PostOverviewCardBigBinding
import com.darwin.photolandhk.posts.PostContent
import com.darwin.photolandhk.posts.wpAPIDateString2Date

class PostBigOverviewAdapter(private val onClickListener: OnClickListener) : ListAdapter<PostContent, PostBigOverviewAdapter.PostViewHolder>(
    DiffCallback
) {

    class PostViewHolder(private var binding: PostOverviewCardBigBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PostContent) {
            binding.post = item
            binding.datePublished = wpAPIDateString2Date(item.date)
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PostContent>() {
        override fun areContentsTheSame(oldItem: PostContent, newItem: PostContent): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: PostContent, newItem: PostContent): Boolean {
            return oldItem === newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(PostOverviewCardBigBinding.inflate(
            LayoutInflater.from(parent.context)
        ))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
//            it.context.startActivity(Intent(it.context, PostActivity::class.java).putExtra("post", item))
        }
        holder.bind(item)
    }

    class OnClickListener(val clickListener: (post: PostContent) -> Unit) {
        fun onClick(post: PostContent) = clickListener(post)
    }

}