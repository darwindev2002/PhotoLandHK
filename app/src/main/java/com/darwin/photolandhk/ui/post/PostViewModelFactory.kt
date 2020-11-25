package com.darwin.photolandhk.ui.post

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.darwin.photolandhk.posts.PostContent

class PostViewModelFactory(private val post: PostContent, private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
            return PostViewModel(post, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}