package com.darwin.photolandhk

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(){

//    private val fragList: List<Fragment> = ArrayList<Fragment>(listOf(HomeFragment(),
//       NewsFragment(), NewsFragment(), DiscussionFragment(), EventsFragment()))
//    private var currentFragment: Fragment = Fragment()

//    private val navListener: BottomNavigationView.OnNavigationItemSelectedListener =
//        BottomNavigationView.OnNavigationItemSelectedListener {item ->
//            loadPage(supportFragmentManager, selected_fragment = item.itemId)
//            return@OnNavigationItemSelectedListener true
//        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        supportFragmentManager.beginTransaction()
//            .add(R.id.fragment_overview_container, fragList[0]).hide(fragList[0])
//            .add(R.id.fragment_overview_container, fragList[1]).hide(fragList[1])
////            .add(R.id.fragment_overview_container, fragList[2]).hide(fragList[2])
//            .add(R.id.fragment_overview_container, fragList[3]).hide(fragList[3])
//            .add(R.id.fragment_overview_container, fragList[4]).hide(fragList[4])
//            .commit()
//
//        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
//        bottomNav.setOnNavigationItemSelectedListener(navListener)
//        bottomNav.selectedItemId = R.id.overview_home

//        if (savedInstanceState == null) {
//            val fragment = HomeFragment()
//            supportFragmentManager.beginTransaction()
//                .add(R.id.nav_host_fragment, fragment)
//                .commit()
//        }
//
//        supportFragmentManager.beginTransaction()
//            .add(R.id.nav_host_fragment, ReportFragment(), "reportFrag")
//            .hide(supportFragmentManager.findFragmentByTag("homeFrag")!!)


        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        val navHostFragment: NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(bottomNav, navHostFragment.navController)

//        supportFragmentManager.beginTransaction()
//            .add(R.id.fragment_overview_container, fragList[0]).hide(fragList[0])
//            .add(R.id.fragment_overview_container, fragList[1]).hide(fragList[1])
//            .add(R.id.fragment_overview_container, fragList[2]).hide(fragList[2])
//            .add(R.id.fragment_overview_container, fragList[3]).hide(fragList[3])
//            .add(R.id.fragment_overview_container, fragList[4]).hide(fragList[4])
//            .commit()



//        val navController = findNavController(R.id.nav_host_fragment)
//        val navigator = KeepStateNavigator(this, navHostFragment.childFragmentManager, R.id.nav_host_fragment)
//        navHostFragment.navController.navigatorProvider += navigator
//
//        navHostFragment.navController.setGraph(R.navigation.nav_graph)
//
//        NavigationUI.setupWithNavController(bottomNav, navHostFragment.navController)

//        MobileAds.initialize(this) {}
//        createAds()

    }

//     fun loadPage(fragmentManager: FragmentManager, selected_fragment: Int) {
//         val frag = when (selected_fragment) {
//            R.id.overview_home -> 0
//            R.id.overview_report -> 1
//            R.id.overview_news -> 2
//            R.id.overview_discussion -> 3
//            R.id.overview_events -> 4
//            else -> 99
//         }
//         fragmentManager.beginTransaction()
//             .hide(currentFragment)
//             .show(fragList[frag])
//             .commit()
//         currentFragment = fragList[frag]
//     }

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