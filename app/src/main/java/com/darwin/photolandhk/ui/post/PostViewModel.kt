package com.darwin.photolandhk.ui.post

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.darwin.photolandhk.network.Api
import com.darwin.photolandhk.network.ApiStatus
import com.darwin.photolandhk.posts.PostContent
import kotlinx.coroutines.launch

class PostViewModel(post: PostContent, app: Application): AndroidViewModel(app) {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status
    private val _selectedPost = MutableLiveData<PostContent>()
    val selectedPost: LiveData<PostContent>
        get() = _selectedPost
    private val _theAuthorThumbnailURL = MutableLiveData<String>()
    val theAuthorThumbnailURL: LiveData<String>
        get() = _theAuthorThumbnailURL

    init {
        _selectedPost.value = post
//        println(post.content.rendered)
        getTAuthorThumbnailIURL()
    }

    fun getTAuthorThumbnailIURL() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _theAuthorThumbnailURL.value = Api.retrofitService.getAuthorThumbnail(_selectedPost.value!!.author).avatar_urls.get(24)
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
//                e.printStackTrace()
                _status.value = ApiStatus.ERROR
                _theAuthorThumbnailURL.value = ""
            }
        }
    }

}