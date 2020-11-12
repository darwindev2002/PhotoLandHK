package com.darwin.photolandhk

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.darwin.photolandhk.ui.DiscussionFragment
import com.darwin.photolandhk.ui.EventsFragment
import com.darwin.photolandhk.ui.ReportsFragment
import com.darwin.photolandhk.ui.home.HomeFragment
import com.darwin.photolandhk.ui.news.NewsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonTransformingSerializer

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

    @Serializable
    data class Project(
        val name: String,
        @Serializable(with = PostListSerializer::class)
        val users: List<Holder.Post>
    )

    object PostListSerializer : JsonTransformingSerializer<List<Holder.Post>>(ListSerializer(Holder.Post.serializer())) {
        override fun transformDeserialize(element: JsonElement): JsonElement =
            if (element !is JsonArray) JsonArray(listOf(element)) else element

    }

}