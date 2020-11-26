package com.darwin.photolandhk.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.darwin.photolandhk.MainActivity
import com.darwin.photolandhk.R
import com.darwin.photolandhk.databinding.FragmentOverviewHomeBinding
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

    fun createNewsRecycler(binding: FragmentOverviewHomeBinding){
        binding.lifecycleOwner = this
        binding.homeNewsCard.viewModel = newsViewModel
        binding.homeNewsCard.homeNewsRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.homeNewsCard.homeNewsRecyclerview.adapter = HomeNewsOverviewAdapter()

        val viewMore: Button = binding.root.findViewById(R.id.button_more_news)
        viewMore.setOnClickListener{
            (activity as MainActivity).updateBottomNav(R.id.overview_news)
        }
    }

    fun createReportRecycler(binding: FragmentOverviewHomeBinding){
        binding.lifecycleOwner = this
        binding.homeReportCard.viewModel = reportViewModel
        binding.homeReportCard.homeReportRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.homeReportCard.homeReportRecyclerview.adapter = HomeReportOverviewAdapter()

        val viewMore: Button = binding.root.findViewById(R.id.button_more_report)
        viewMore.setOnClickListener{
            (activity as MainActivity).updateBottomNav(R.id.overview_report)
        }
    }

    fun createDiscussionRecycler(binding: FragmentOverviewHomeBinding){
        binding.lifecycleOwner = this
        binding.homeDiscussionCard.viewModel = discussionViewModel
        binding.homeDiscussionCard.homeDiscussionRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.homeDiscussionCard.homeDiscussionRecyclerview.adapter = HomeDiscussionOverviewAdapter()

        val viewMore: Button = binding.root.findViewById(R.id.button_more_discussion)
        viewMore.setOnClickListener{
            (activity as MainActivity).updateBottomNav(R.id.overview_discussion)
        }
    }

}