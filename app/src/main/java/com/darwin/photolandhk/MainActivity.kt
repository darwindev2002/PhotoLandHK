package com.darwin.photolandhk

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.darwin.photolandhk.ui.DiscussionFragment
import com.darwin.photolandhk.ui.EventsFragment
import com.darwin.photolandhk.ui.home.HomeFragment
import com.darwin.photolandhk.ui.news.NewsFragment
import com.darwin.photolandhk.ui.report.ReportFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

//    private val fragList: List<Fragment> = ArrayList<Fragment>(listOf(HomeFragment(),
//       NewsFragment(), NewsFragment(), DiscussionFragment(), EventsFragment()))
//    private var currentFragment: Fragment = Fragment()

    private val IDs = listOf(
        R.id.overview_home,
        R.id.overview_report,
        R.id.overview_news,
        R.id.overview_discussion,
        R.id.overview_events
    )

    private val navListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {item ->
            loadFragmentById(item.itemId)
            return@OnNavigationItemSelectedListener true
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(navListener)
        bottomNav.selectedItemId = R.id.overview_home

//        MobileAds.initialize(this) {}
//        createAds()

    }

    fun loadFragmentById(id: Int) {
        var transaction = supportFragmentManager.beginTransaction()
        transaction =
            if (supportFragmentManager.findFragmentByTag(id.toString()) == null)
                transaction.add(R.id.fragment_overview_container, createCorrespondingFragment(id),id.toString())
            else
                transaction.show(supportFragmentManager.findFragmentByTag(id.toString())!!)
        val currentFrag = getCurrentFragment()
        if (currentFrag != null) transaction = transaction.hide(currentFrag)
        transaction.commit()
    }

    fun createCorrespondingFragment(id: Int) = when (id) {
        R.id.overview_home -> HomeFragment()
        R.id.overview_report -> ReportFragment()
        R.id.overview_news -> NewsFragment()
        R.id.overview_discussion -> DiscussionFragment()
        R.id.overview_events -> EventsFragment()
        else -> Fragment()
    }

    fun getCurrentFragment(): Fragment? = run {
        for (id in IDs) {
            val frag = supportFragmentManager.findFragmentByTag(id.toString()) ?: continue
            if (frag.isVisible) return frag
        }
        return null
    }

    fun updateBottomNav(id: Int) {
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.selectedItemId = id
    }

    fun startBrowserLaowa(view: View) {
        val url = "https://www.laowa.com.hk"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

}