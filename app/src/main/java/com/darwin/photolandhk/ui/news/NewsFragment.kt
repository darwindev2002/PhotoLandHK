package com.darwin.photolandhk.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.darwin.photolandhk.R
import com.darwin.photolandhk.databinding.FragmentOverviewNewsBinding

class NewsFragment : Fragment(),SwipeRefreshLayout.OnRefreshListener {

    private val viewModel: NewsViewModel by lazy {
        ViewModelProvider(this).get(NewsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverviewNewsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.newsRecyclerview.adapter = NewsOverviewAdapter(NewsOverviewAdapter.OnClickListener {
            viewModel.displayPostContent(it)
        })

        val swipeLayout: SwipeRefreshLayout = binding.root.findViewById(R.id.swipe_container_news)
        swipeLayout.setOnRefreshListener {
            viewModel.getNewsOverview(swipeLayout)
        }

        viewModel.navigateToSelectedProperty.observe(this, Observer {
            if (it != null) {
                activity?.findNavController(R.id.nav_graph)?.navigate(NewsFragmentDirections.actionShowPost(it))
                viewModel.displayPostContentComplete()
            }
        })
        return binding.root
    }

    override fun onRefresh(){

    }

}