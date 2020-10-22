package com.darwin.photolandhk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.darwin.photolandhk.ui.DiscussionFragment
import com.darwin.photolandhk.ui.EventsFragment
import com.darwin.photolandhk.ui.NewsFragment
import com.darwin.photolandhk.ui.ReportsFragment
import com.darwin.photolandhk.ui.home.home_cards.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(){

    private val navListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {item ->
            loadPage(supportFragmentManager, item.itemId)
            return@OnNavigationItemSelectedListener true
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(navListener)
        bottomNav.selectedItemId = R.id.nav_home

//        MobileAds.initialize(this) {}
//        createAds()
    }

    fun loadPage(fragmentManager: FragmentManager, id: Int) {
        val selectedFragment = when (id) {
            R.id.nav_report -> ReportsFragment()
            R.id.nav_news -> NewsFragment()
            R.id.nav_home -> HomeFragment()
            R.id.nav_discussion -> DiscussionFragment()
            R.id.nav_events -> EventsFragment()
            else -> null
        }
        if (selectedFragment != null) {
            fragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment).commit()
        }
    }

}