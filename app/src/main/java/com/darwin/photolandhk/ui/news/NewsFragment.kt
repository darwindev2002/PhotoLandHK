package com.darwin.photolandhk.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.darwin.photolandhk.MainActivity
import com.darwin.photolandhk.R
import com.darwin.photolandhk.databinding.FragmentOverviewNewsBinding

class NewsFragment : Fragment(){

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

        val swipeLayout = binding.swipeContainerNews
        swipeLayout.setOnRefreshListener {
            viewModel.getNewsOverview()
            swipeLayout.isRefreshing = false
        }

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                (activity as MainActivity).updateFragment(R.id.post,it)
            }
        })
        return binding.root
    }

}