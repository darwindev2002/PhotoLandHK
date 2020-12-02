package com.darwin.photolandhk.ui.home.home_cards

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.darwin.photolandhk.databinding.HomeNewsItemBinding
import com.darwin.photolandhk.posts.PostContent

class HomeNewsOverviewAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<PostContent, HomeNewsOverviewAdapter.HomeNewsPostViewHolder>(
        DiffCallback
    ) {

    class HomeNewsPostViewHolder(private var binding: HomeNewsItemBinding) :
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNewsPostViewHolder {
        return HomeNewsPostViewHolder(
            HomeNewsItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: HomeNewsPostViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    class OnClickListener(val clickListener: (post: PostContent) -> Unit) {
        fun onClick(post: PostContent) = clickListener(post)
    }

}