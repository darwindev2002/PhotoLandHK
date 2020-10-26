package com.darwin.photolandhk.ui.home.home_cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import com.darwin.photolandhk.FragmentUpdate.loadPage
import com.darwin.photolandhk.R

class HomeFragment : Fragment() {

//    val carouselViewImages: IntArray = intArrayOf(R.drawable.pancake_lens_cap_for_leica_m_mount_2,
//        R.drawable.rf_lens_value_up, R.drawable.sony_a7c, R.drawable.subanan)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
//        createCarouselView(view)
//        val adView: ImageView = view.findViewById(R.id.ad_header)
//        adView.setImageResource(R.drawable.cshine_942x135)
        createNewsRecycler(view)
        createReportRecycler(view)
        createDiscussionRecycler(view)
        return view
    }

//    fun createCarouselView(view: View){
//        val carouselView: CarouselView = view.findViewById(R.id.carouselView)
//        carouselView.pageCount = carouselViewImages.size
//        carouselView.setImageListener { position, imageView -> imageView.setImageResource(carouselViewImages[position]) }
//    }

    fun createNewsRecycler(view: View){
        val newsRecyvlerView: RecyclerView = view.findViewById(R.id.home_news_recyclerview)
        newsRecyvlerView.setHasFixedSize(true)
        newsRecyvlerView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        newsRecyvlerView.adapter = HomeNewsAdapter()

        val viewMore: TextView = view.findViewById(R.id.nav_news_more)
//        viewMore.setOnClickListener{ loadPage(this.parentFragmentManager,R.id.nav_news) }
    }

    fun createReportRecycler(view: View){
        val reportRecyclerView: RecyclerView = view.findViewById(R.id.home_report_recyclerview)
        reportRecyclerView.setHasFixedSize(true)
        reportRecyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        reportRecyclerView.adapter = HomeReportAdapter()

        val viewMore: TextView = view.findViewById(R.id.nav_report_more)
//        viewMore.setOnClickListener{ loadPage(this.parentFragmentManager,R.id.nav_report) }
    }

    fun createDiscussionRecycler(view: View){
        val discussionRecyclerView: RecyclerView = view.findViewById(R.id.home_discussion_recyclerview)
        discussionRecyclerView.setHasFixedSize(true)
        discussionRecyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        discussionRecyclerView.adapter = HomeDiscussionAdapter()

        val viewMore: TextView = view.findViewById(R.id.nav_discussion_more)
//        viewMore.setOnClickListener{ loadPage(this.parentFragmentManager,R.id.nav_discussion) }
    }

}