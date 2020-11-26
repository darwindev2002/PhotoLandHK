package com.darwin.photolandhk.ui.report

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darwin.photolandhk.network.Api
import com.darwin.photolandhk.network.ApiStatus
import com.darwin.photolandhk.network.categoryList
import com.darwin.photolandhk.posts.PostContent
import kotlinx.coroutines.launch
import java.util.*

class ReportViewModel : ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status
    private val _posts = MutableLiveData<List<PostContent>>()
    val posts: LiveData<List<PostContent>>
        get() = _posts
    private val _navigateToSelectedProperty = MutableLiveData<PostContent>()
    val navigateToSelectedProperty: LiveData<PostContent>
        get() = _navigateToSelectedProperty

    init {
        getReportOverview()
    }

    private fun getReportOverview() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                val categoryId = categoryList["真．用家報告"] ?: error("Error: category not found")
                val count = Api.retrofitService.getCategoryPostCount(categoryId).count
                _posts.value = Api.retrofitService.getPostList(pages=count,category=categoryId)
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
//                e.printStackTrace()
                _status.value = ApiStatus.ERROR
                _posts.value = ArrayList()
            }
        }
    }

    fun displayPostContent(post: PostContent) {
        _navigateToSelectedProperty.value = post
    }

    fun displayPostContentComplete() {
        _navigateToSelectedProperty.value = null
    }

}