package com.darwin.photolandhk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.darwin.photolandhk.posts.PostContent
import com.darwin.photolandhk.ui.DiscussionFragment
import com.darwin.photolandhk.ui.EventsFragment
import com.darwin.photolandhk.ui.OverviewFragment
import com.darwin.photolandhk.ui.home.HomeFragment
import com.darwin.photolandhk.ui.news.NewsFragment
import com.darwin.photolandhk.ui.post.PostFragment
import com.darwin.photolandhk.ui.report.ReportFragment

class MainActivity : AppCompatActivity() {

    val overviewFragment = OverviewFragment()
    private var showingPost: PostFragment? = null


    /*
     * List of ID's of the overivew pages for navigation
     */
    private val IDs = listOf(
        R.id.overview_home,
        R.id.overview_report,
        R.id.overview_news,
        R.id.overview_discussion,
        R.id.overview_events
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.titleToolbar))
        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, overviewFragment, R.id.overview_container.toString()).commit()
    }

    private fun createCorrespondingFragment(id: Int) = when (id) {
        R.id.overview_home -> HomeFragment()
        R.id.overview_report -> ReportFragment()
        R.id.overview_news -> NewsFragment()
        R.id.overview_discussion -> DiscussionFragment()
        R.id.overview_events -> EventsFragment()
        else -> null
    }

    private fun getCurrentFragment(): Fragment? = run {
        return supportFragmentManager.findFragmentByTag(overviewFragment.currentVisibleFragmentByID.toString())
    }

    /*
     * Update fragment containers
     */
    fun updateFragment(id: Int, post: PostContent? = null) {
        if (getCurrentFragment() != null && id == overviewFragment.currentVisibleFragmentByID) return
        val transaction = supportFragmentManager.beginTransaction()
        // Navigate to PostFragment
        if (id == R.id.post) transaction.add(
            R.id.main_container,
            PostFragment(post!!),
            R.id.post.toString()
        ).hide(overviewFragment).addToBackStack(R.id.post.toString())
        // Update (in PostFragment) / Pop back to (in OverviewFragment) OverviewFragment
        else {
            if (showingPost != null) {
                // When in PostFragment
                supportFragmentManager.popBackStack()
                return
            } else {
                // When in OverviewFragment
                val newFrag = supportFragmentManager.findFragmentByTag(id.toString())
                println(newFrag)
                if (newFrag != null) transaction.show(newFrag)
                else transaction.add(
                    R.id.fragment_overview_container,
                    createCorrespondingFragment(id)!!,
                    id.toString()
                )
                getCurrentFragment()?.let { println(it);transaction.hide(it) }
            }
        }
        transaction.commit()
    }
}