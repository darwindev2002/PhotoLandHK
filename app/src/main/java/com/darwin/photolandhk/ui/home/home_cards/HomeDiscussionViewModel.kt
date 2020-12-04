package com.darwin.photolandhk.ui.home.home_cards

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darwin.photolandhk.network.Api
import com.darwin.photolandhk.network.ApiStatus
import com.darwin.photolandhk.network.categoryList
import com.darwin.photolandhk.network.homeOverviewListSize
import com.darwin.photolandhk.posts.PostContent
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.min

class HomeDiscussionViewModel : ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status
    private val _posts = MutableLiveData<List<PostContent>>()
    val posts: LiveData<List<PostContent>>
        get() = _posts
    private val _navigateToSelectedPost = MutableLiveData<PostContent>()
    val navigateToSelectedPost: LiveData<PostContent>
        get() = _navigateToSelectedPost

    lateinit var skeletonView: View

    init {
        getDiscussionSmallOverview()
    }

    private fun getDiscussionSmallOverview() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                val categoryId = categoryList["攝影齊齊傾"] ?: error("Error: category not found")
                val count = min(Api.retrofitService.getCategoryPostCount(categoryId).count, homeOverviewListSize)
                _posts.value = Api.retrofitService.getPostList(pages=count,category=categoryId)
                _status.value = ApiStatus.DONE
                skeletonView.visibility = View.GONE
            } catch (e: Exception) {
//                e.printStackTrace()
                _status.value = ApiStatus.ERROR
                _posts.value = ArrayList()
            }
        }
    }

    fun displayPostContent(post: PostContent) {
        _navigateToSelectedPost.value = post
    }
//
//    fun displayPostContentComplete() {
//        _navigateToSelectedPost.value = null
//    }

}