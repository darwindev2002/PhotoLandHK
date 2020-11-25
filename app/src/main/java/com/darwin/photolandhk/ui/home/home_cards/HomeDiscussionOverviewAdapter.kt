package com.darwin.photolandhk.ui.home.home_cards

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.darwin.photolandhk.databinding.HomeDiscussionItemBinding
import com.darwin.photolandhk.posts.PostContent

class HomeDiscussionOverviewAdapter() :
    ListAdapter<PostContent, HomeDiscussionOverviewAdapter.HomeDiscussionPostViewHolder>(
        DiffCallback
    ) {

    class HomeDiscussionPostViewHolder(private var binding: HomeDiscussionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PostContent) {
            binding.post = item
            binding.isHomeOverview = true
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeDiscussionPostViewHolder {
        return HomeDiscussionPostViewHolder(
            HomeDiscussionItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: HomeDiscussionPostViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
//            onClickListener.onClick(item)
//            it.context.startActivity(Intent(it.context, PostActivity::class.java).putExtra("post", item))
        }
        holder.bind(item)
    }

    class OnClickListener(val clickListener: (post: PostContent) -> Unit) {
        fun onClick(post: PostContent) = clickListener(post)
    }

}