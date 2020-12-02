package com.darwin.photolandhk.ui.report

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darwin.photolandhk.network.Api
import com.darwin.photolandhk.network.ApiStatus
import com.darwin.photolandhk.network.LOAD_PER_TIME
import com.darwin.photolandhk.network.categoryList
import com.darwin.photolandhk.posts.PostContent
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.ceil

class ReportViewModel : ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status
    private val _posts = MutableLiveData<MutableList<PostContent>>()
    val posts: LiveData<MutableList<PostContent>>
        get() = _posts
    private val _navigateToSelectedPost = MutableLiveData<PostContent>()
    val navigateToSelectedPost: LiveData<PostContent>
        get() = _navigateToSelectedPost

    val categoryId = categoryList["真．用家報告"] ?: error("Error: category not found")
    private var gotPosts = 0

    init {
        getReportOverview(true)
    }

    fun getReportOverview(isInit: Boolean = false) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                val count = Api.retrofitService.getCategoryPostCount(categoryId).count
                if (isInit) {
                    _posts.value = Api.retrofitService.getPostList(pages= LOAD_PER_TIME,category=categoryId) as MutableList
                    gotPosts = _posts.value!!.size
                } else if (count > gotPosts) {
                    _posts.value?.addAll(
                        Api.retrofitService.getPostList(
                            pages = LOAD_PER_TIME,
                            page = (ceil(gotPosts*1.0/ LOAD_PER_TIME) +1).toInt(),
                            category = categoryId
                        )
                    )
                    gotPosts = _posts.value!!.size
                }
                _status.value = ApiStatus.DONE
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

    fun displayPostContentComplete() {
        _navigateToSelectedPost.value = null
    }

}