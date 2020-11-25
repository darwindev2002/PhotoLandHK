package com.darwin.photolandhk.ui.post

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.darwin.photolandhk.posts.PostContent

class PostViewModel(post: PostContent, app: Application): AndroidViewModel(app) {

    private val _selectedPost = MutableLiveData<PostContent>()
    val selectedPost: LiveData<PostContent>
        get() = _selectedPost

    init {
        _selectedPost.value = post
    }

}