package com.darwin.photolandhk.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.darwin.photolandhk.databinding.FragmentPostBinding
import com.darwin.photolandhk.posts.PostContent
import com.darwin.photolandhk.posts.wpAPIDateString2Date

class PostFragment(post: PostContent) : Fragment() {

    private val _selectedPost = MutableLiveData<PostContent>()
    val selectedPost: LiveData<PostContent>
        get() = _selectedPost

    init {
        _selectedPost.value = post
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentPostBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val viewModelFactory = PostViewModelFactory(selectedPost.value!!, application)
        binding.viewModel = ViewModelProvider(this, viewModelFactory).get(PostViewModel::class.java)
        binding.datePublished = wpAPIDateString2Date(_selectedPost.value!!.date)
        binding.isTrue = true

        return binding.root
    }
}