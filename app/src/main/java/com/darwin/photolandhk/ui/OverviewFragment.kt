package com.darwin.photolandhk.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.darwin.photolandhk.MainActivity
import com.darwin.photolandhk.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.properties.Delegates

class OverviewFragment : Fragment() {

    private lateinit var bottomNav: BottomNavigationView
    var currentVisibleFragmentByID by Delegates.notNull<Int>()

    private val navListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {item ->
            (activity as MainActivity).updateFragment(item.itemId)
            currentVisibleFragmentByID = item.itemId
            return@OnNavigationItemSelectedListener true
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_overview,container,false)

        bottomNav = view.findViewById(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(navListener)
        currentVisibleFragmentByID = R.id.overview_home
        bottomNav.selectedItemId = R.id.overview_home

        return view
    }

    /*
     * Update the bottom navigation view and visible fragment as well;
     * Create post fragment if needed
     */
    fun updateBottomNav(id: Int) {
        bottomNav.selectedItemId = id
//        (activity as MainActivity).updateFragment(id)
    }

}
