package com.darwin.photolandhk

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.darwin.photolandhk.ui.DiscussionFragment
import com.darwin.photolandhk.ui.EventsFragment
import com.darwin.photolandhk.ui.home.HomeFragment
import com.darwin.photolandhk.ui.news.NewsFragment
import com.darwin.photolandhk.ui.report.ReportFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(){

    private val fragList: List<Fragment> = ArrayList<Fragment>(listOf(HomeFragment(), ReportFragment(), NewsFragment(), DiscussionFragment(), EventsFragment()))
    private var currentFragment: Fragment = Fragment()

    private val navListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {item ->
            loadPage(supportFragmentManager, selected_fragment = item.itemId)
            return@OnNavigationItemSelectedListener true
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_overview_container, fragList[0]).hide(fragList[0])
            .add(R.id.fragment_overview_container, fragList[1]).hide(fragList[1])
//            .add(R.id.fragment_overview_container, fragList[2]).hide(fragList[2])
            .add(R.id.fragment_overview_container, fragList[3]).hide(fragList[3])
            .add(R.id.fragment_overview_container, fragList[4]).hide(fragList[4])
            .commit()

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(navListener)
        bottomNav.selectedItemId = R.id.nav_home

//        MobileAds.initialize(this) {}
//        createAds()

    }

     fun loadPage(fragmentManager: FragmentManager, selected_fragment: Int) {
         val frag = when (selected_fragment) {
            R.id.nav_home -> 0
            R.id.nav_report -> 1
            R.id.nav_news -> 2
            R.id.nav_discussion -> 3
            R.id.nav_events -> 4
            else -> 99
         }
         fragmentManager.beginTransaction()
             .hide(currentFragment)
             .show(fragList[frag])
             .commit()
         currentFragment = fragList[frag]
     }

    fun updateBottomNav(id: Int){
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.selectedItemId = id
    }

    fun startBrowserLaowa(view: View){
        val url = "https://www.laowa.com.hk"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

}