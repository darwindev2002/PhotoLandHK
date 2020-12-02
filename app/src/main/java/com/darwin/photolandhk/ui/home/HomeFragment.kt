package com.darwin.photolandhk.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.darwin.photolandhk.MainActivity
import com.darwin.photolandhk.R
import com.darwin.photolandhk.databinding.FragmentOverviewHomeBinding
import com.darwin.photolandhk.ui.OverviewFragment
import com.darwin.photolandhk.ui.home.home_cards.*

class HomeFragment : Fragment() {

    private val newsViewModel: HomeNewsViewModel by lazy {
        ViewModelProvider(this).get(HomeNewsViewModel::class.java)
    }
    private val reportViewModel: HomeReportViewModel by lazy {
        ViewModelProvider(this).get(HomeReportViewModel::class.java)
    }
    private val discussionViewModel: HomeDiscussionViewModel by lazy {
        ViewModelProvider(this).get(HomeDiscussionViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, R.layout.fragment_overview_home, container, false) as FragmentOverviewHomeBinding

        createNewsRecycler(binding)
        createReportRecycler(binding)
        createDiscussionRecycler(binding)
        return binding.root
    }

//    fun createCarouselView(view: View){
//        val carouselView: CarouselView = view.findViewById(R.id.carouselView)
//        carouselView.pageCount = carouselViewImages.size
//        carouselView.setImageListener { position, imageView -> imageView.setImageResource(carouselViewImages[position]) }
//    }

    private fun createNewsRecycler(binding: FragmentOverviewHomeBinding){
        binding.lifecycleOwner = this
        binding.homeNewsCard.viewModel = newsViewModel
        newsViewModel.navigateToSelectedPost.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                (activity as MainActivity).updateFragment(R.id.post,it)
            }
        })
        binding.homeNewsCard.homeNewsRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.homeNewsCard.homeNewsRecyclerview.adapter = HomeNewsOverviewAdapter(HomeNewsOverviewAdapter.OnClickListener {
            newsViewModel.displayPostContent(it)
        })

        val viewMore: Button = binding.root.findViewById(R.id.button_more_news)
        viewMore.setOnClickListener{
            (parentFragmentManager.findFragmentByTag(R.id.overview_container.toString()) as OverviewFragment).updateBottomNav(R.id.overview_news)
        }
    }

    private fun createReportRecycler(binding: FragmentOverviewHomeBinding){
        binding.lifecycleOwner = this
        binding.homeReportCard.viewModel = reportViewModel
        reportViewModel.navigateToSelectedPost.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                (activity as MainActivity).updateFragment(R.id.post,it)
            }
        })
        binding.homeReportCard.homeReportRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.homeReportCard.homeReportRecyclerview.adapter = HomeReportOverviewAdapter(HomeReportOverviewAdapter.OnClickListener {
            reportViewModel.displayPostContent(it)
        })

        val viewMore: Button = binding.root.findViewById(R.id.button_more_report)
        viewMore.setOnClickListener{
            (parentFragmentManager.findFragmentByTag(R.id.overview_container.toString()) as OverviewFragment).updateBottomNav(R.id.overview_report)
        }
    }

    private fun createDiscussionRecycler(binding: FragmentOverviewHomeBinding){
        binding.lifecycleOwner = this
        binding.homeDiscussionCard.viewModel = discussionViewModel
        discussionViewModel.navigateToSelectedPost.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                (activity as MainActivity).updateFragment(R.id.post,it)
            }
        })
        binding.homeDiscussionCard.homeDiscussionRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.homeDiscussionCard.homeDiscussionRecyclerview.adapter = HomeDiscussionOverviewAdapter(HomeDiscussionOverviewAdapter.OnClickListener {
            discussionViewModel.displayPostContent(it)
        })

        val viewMore: Button = binding.root.findViewById(R.id.button_more_discussion)
        viewMore.setOnClickListener{
            (parentFragmentManager.findFragmentByTag(R.id.overview_container.toString()) as OverviewFragment).updateBottomNav(R.id.overview_discussion)
        }
    }

}