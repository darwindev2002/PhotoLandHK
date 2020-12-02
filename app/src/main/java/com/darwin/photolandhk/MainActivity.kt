package com.darwin.photolandhk

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
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

    //    var currentVisibleFragmentByID by Delegates.notNull<Int>()
    val overviewFragment = OverviewFragment()

    //    var fragmentStack = Stack<Int>()
    private var showingPost: PostFragment? = null
//    private lateinit var bottomNav: BottomNavigationView

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

//    private val navListener: BottomNavigationView.OnNavigationItemSelectedListener =
//        BottomNavigationView.OnNavigationItemSelectedListener {item ->
//            loadFragmentById(item.itemId)
//            return@OnNavigationItemSelectedListener true
//        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, overviewFragment, R.id.overview_container.toString()).commit()
//        bottomNav = findViewById(R.id.bottom_navigation)
//        bottomNav.setOnNavigationItemSelectedListener(navListener)
//        currentVisibleFragmentByID = R.id.overview_home
////        bottomNav.selectedItemId = R.id.overview_home

//        MobileAds.initialize(this) {}
//        createAds()

    }

//    override fun onBackPressed() {
//        when {
//            showingPost -> {bottomNav.visibility = View.VISIBLE; loadFragmentById(fragmentStack.pop());}
//            overviewFragment.currentVisibleFragmentByID != R.id.overview_home -> loadFragmentById(R.id.overview_home)
//            else -> super.onBackPressed()
//        }
//    }

//    private fun loadOverivewFragmentById(id: Int, post: PostContent? = null) {
//        val transaction = supportFragmentManager.beginTransaction()
//
//        if (showingPost != null) transaction.remove(showingPost!!)
//        else {
//            transaction.replace(
//                R.id.fragment_overview_container,
//                supportFragmentManager.findFragmentById(id)
//            )
//        }
//        if (id == R.id.post || supportFragmentManager.findFragmentByTag(id.toString()) == null)
//            transaction.add(
//                R.id.main_container,
//                createCorrespondingFragment(id, post),
//                id.toString()
//            )
//        else
//            transaction.show(supportFragmentManager.findFragmentByTag(id.toString())!!)
////        val currentFrag = getCurrentFragment()
////        if (currentFrag != null){
////            if (currentVisibleFragmentByID == R.id.post) transaction.remove(currentFrag)
////            if (currentVisibleFragmentByID == id) return
////            else transaction = transaction.hide(currentFrag)
////        }
////        transaction.commit()
////        currentVisibleFragmentByID = id
//    }

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
//        if (showingPost != null) {
//            supportFragmentManager.beginTransaction().remove(showingPost!!).commit()
//        }
//        if (id != R.id.post) {
//            loadOverivewFragmentById(id)
//        } else {
//            showingPost = PostFragment(post!!)
//            supportFragmentManager.beginTransaction()
//                .replace(
//                    R.id.main_container,
//                    PostFragment(post!!),
//                    R.id.overview_container.toString()
//                )
//                .addToBackStack(R.id.overview_container.toString())
//                .commit()
//        }
    }

    private fun startBrowserLaowa(view: View) {
        val url = "https://www.laowa.com.hk"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

}