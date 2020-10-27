package com.darwin.photolandhk.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.darwin.photolandhk.R

class NewsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        createNewsRecycler(view)
        return view
    }

    fun createNewsRecycler(view: View){
        val newsRecyclerView: RecyclerView = view.findViewById(R.id.news_recyclerview)
        newsRecyclerView.setHasFixedSize(true)
        newsRecyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        newsRecyclerView.adapter = NewsAdapter()
    }

}