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

//        println("----------------------------------------"+getJson())

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

    fun testing(){
//        val listOfPosts = """[[{"id":10464,"date":"2020-11-04T07:25:14","date_gmt":"2020-11-03T23:25:14","guid":{"rendered":"https:\/\/photolandhk.com\/?p=10464"},"modified":"2020-11-03T10:08:22","modified_gmt":"2020-11-03T02:08:22","slug":"panasonic-85mm-f-1-8-l-mount-%e9%8f%a1%e9%a0%ad%e8%a6%8f%e6%a0%bc%e6%b4%a9%e6%bc%8f-%e9%a0%90%e8%a8%8811%e6%9c%885%e6%97%a5%e7%99%bc%e5%b8%83","status":"publish","type":"post","link":"https:\/\/photolandhk.com\/panasonic-85mm-f-1-8-l-mount-%e9%8f%a1%e9%a0%ad%e8%a6%8f%e6%a0%bc%e6%b4%a9%e6%bc%8f-%e9%a0%90%e8%a8%8811%e6%9c%885%e6%97%a5%e7%99%bc%e5%b8%83\/","title":{"rendered":"Panasonic 85mm f\/1.8 L mount \u93e1\u982d\u898f\u683c\u6d29\u6f0f\u2013\u9810\u8a0811\u67085\u65e5\u767c\u5e03"},"content":{"rendered":"\n<figure class=\"wp-block-image size-large\"><img src=\"https:\/\/photolandhk.com\/wp-content\/uploads\/2020\/11\/Panasonic-85mm-f1.8-S-lens-for-L-mount.jpg\" alt=\"\" class=\"wp-image-10470\" srcset=\"https:\/\/photolandhk.com\/wp-content\/uploads\/2020\/11\/Panasonic-85mm-f1.8-S-lens-for-L-mount.jpg 540w, https:\/\/photolandhk.com\/wp-content\/uploads\/2020\/11\/Panasonic-85mm-f1.8-S-lens-for-L-mount-271x300.jpg 271w\" sizes=\"(max-width: 540px) 100vw, 540px\" \/><\/figure>\n\n\n\n<p>Panasonic \u5373\u5c07\u5ba3\u5e03\u63a8\u51fa\u7528\u65bc L \u63a5\u53e3\u7684\u65b0\u578b 85mm f\/1.8 S \u93e1\u982d\uff0c\u7e7c\u7e8c\u64f4\u5927 L \u93e1\u93e1\u7fa4\u3002\u64da Nokishita \u6d29\u9732\u7684\u6d88\u606f\u770b\u4f86\uff0c\u5b83\u751a\u81f3\u5c07\u5728\u77ed\u77ed\u4e09\u5929\u5167\u5230\u4f86\u3002<\/p>\n\n\n\n<figure class=\"wp-block-image size-large\"><img src=\"https:\/\/photolandhk.com\/wp-content\/uploads\/2020\/11\/\u87a2\u5e55\u622a\u5716-2020-11-02-\u4e0b\u534810.24.59-1024x618.png\" alt=\"\" class=\"wp-image-10467\" srcset=\"https:\/\/photolandhk.com\/wp-content\/uploads\/2020\/11\/\u87a2\u5e55\u622a\u5716-2020-11-02-\u4e0b\u534810.24.59-1024x618.png 1024w, https:\/\/photolandhk.com\/wp-content\/uploads\/2020\/11\/\u87a2\u5e55\u622a\u5716-2020-11-02-\u4e0b\u534810.24.59-497x300.png 497w, https:\/\/photolandhk.com\/wp-content\/uploads\/2020\/11\/\u87a2\u5e55\u622a\u5716-2020-11-02-\u4e0b\u534810.24.59-768x463.png 768w, https:\/\/photolandhk.com\/wp-content\/uploads\/2020\/11\/\u87a2\u5e55\u622a\u5716-2020-11-02-\u4e0b\u534810.24.59-780x470.png 780w, https:\/\/photolandhk.com\/wp-content\/uploads\/2020\/11\/\u87a2\u5e55\u622a\u5716-2020-11-02-\u4e0b\u534810.24.59-600x362.png 600w, https:\/\/photolandhk.com\/wp-content\/uploads\/2020\/11\/\u87a2\u5e55\u622a\u5716-2020-11-02-\u4e0b\u534810.24.59.png 1316w\" sizes=\"(max-width: 1024px) 100vw, 1024px\" \/><\/figure>\n\n\n\n<p>\u6d29\u6f0f\u5305\u62ec\u76f8\u6a5f\u7684\u5b8c\u6574\u898f\u683c\uff08\u986f\u7136\u662f\u6ce2\u862d\u8a9e\uff09\u3002Nokishita \u9084\u8868\u793a\uff0c\u9810\u8a08\u5b83\u5c07\u572811\u67085\u65e5\u5728\u65e5\u672c\u9032\u884c\u9810\u8a02\uff0c\u4f46\u9084\u4e0d\u77e5\u9053\u6703\u5426\u5728\u5168\u7403\u767c\u552e\u3002<\/p>\n\n\n\n<figure class=\"wp-block-image size-large\"><img src=\"https:\/\/photolandhk.com\/wp-content\/uploads\/2020\/11\/\u87a2\u5e55\u622a\u5716-2020-11-02-\u4e0b\u534810.25.11-1024x701.png\" alt=\"\" class=\"wp-image-10468\" srcset=\"https:\/\/photolandhk.com\/wp-content\/uploads\/2020\/11\/\u87a2\u5e55\u622a\u5716-2020-11-02-\u4e0b\u534810.25.11-1024x701.png 1024w, https:\/\/photolandhk.com\/wp-content\/uploads\/2020\/11\/\u87a2\u5e55\u622a\u5716-2020-11-02-\u4e0b\u534810.25.11-439x300.png 439w, https:\/\/photolandhk.com\/wp-content\/uploads\/2020\/11\/\u87a2\u5e55\u622a\u5716-2020-11-02-\u4e0b\u534810.25.11-768x525.png 768w, https:\/\/photolandhk.com\/wp-content\/uploads\/2020\/11\/\u87a2\u5e55\u622a\u5716-2020-11-02-\u4e0b\u534810.25.11-220x150.png 220w, https:\/\/photolandhk.com\/wp-content\/uploads\/2020\/11\/\u87a2\u5e55\u622a\u5716-2020-11-02-\u4e0b\u534810.25.11-600x410.png 600w, https:\/\/photolandhk.com\/wp-content\/uploads\/2020\/11\/\u87a2\u5e55\u622a\u5716-2020-11-02-\u4e0b\u534810.25.11.png 1412w\" sizes=\"(max-width: 1024px) 100vw, 1024px\" \/><\/figure>\n\n\n\n<p>\u6709\u8da3\u7684\u662f\uff0cPanasonic \u4f3c\u4e4e\u5e0c\u671b\u5c07\u50cf\u4eba\u50cf\u93e1\u982d\u7684\u7126\u8ddd\u63d0\u9ad8\u523085mm\uff0c\u7136\u5f8c\u518d\u4f7f\u7528\u8def\u7dda\u66f4\u5ee3\u768424mm\uff0c35mm\u548c50mm f\/1.8 \u901a\u7528\u93e1\u982d\u3002<\/p>\n\n\n\n<p>\u5373\u5c07\u63a8\u51fa\u7684 Panasonic S 85mm f\/1.8 L \u93e1\u982d\u7684\u50f9\u683c\u5c1a\u7121\u5b9a\u8ad6\uff0c\u4f46\u4e5f\u8a31\u5728\u77ed\u77ed\u5e7e\u5929\u4e4b\u5167\u5c31\u6703\u63ed\u76c5\u3002\u5e0c\u671b\u5b83\u4e0d\u6703\u6210\u70ba\u4e00\u500b\u904e\u65bc\u6602\u8cb4\u548c\u904e\u5ea6\u8a2d\u8a08\u7684\u93e1\u982d\uff0c\u800c\u662f\u5c07\u76ee\u6a19\u5c0d\u6e96\u5728\u4e00\u500b\u8f03\u4f4e\u7684\u50f9\u683c\u9ede\uff0c\u65e8\u5728\u5c07\u66f4\u591a\u7684\u5165\u9580\u7d1a\u7528\u6236\u5f15\u5165 L mount \u751f\u614b\u7cfb\u7d71\uff0c\u56e0\u70ba\u9019\u6b63\u662f Panasonic \u6240\u6025\u9700\u7684\u3002<\/p>\n\n\n\n<figure class=\"wp-block-image size-large\"><img src=\"https:\/\/photolandhk.com\/wp-content\/uploads\/2020\/11\/\u87a2\u5e55\u622a\u5716-2020-11-02-\u4e0b\u534810.25.18-1024x772.png\" alt=\"\" class=\"wp-image-10469\" srcset=\"https:\/\/photolandhk.com\/wp-content\/uploads\/2020\/11\/\u87a2\u5e55\u622a\u5716-2020-11-02-\u4e0b\u534810.25.18-1024x772.png 1024w, https:\/\/photolandhk.com\/wp-content\/uploads\/2020\/11\/\u87a2\u5e55\u622a\u5716-2020-11-02-\u4e0b\u534810.25.18-398x300.png 398w, https:\/\/photolandhk.com\/wp-content\/uploads\/2020\/11\/\u87a2\u5e55\u622a\u5716-2020-11-02-\u4e0b\u534810.25.18-768x579.png 768w, https:\/\/photolandhk.com\/wp-content\/uploads\/2020\/11\/\u87a2\u5e55\u622a\u5716-2020-11-02-\u4e0b\u534810.25.18-600x453.png 600w, https:\/\/photolandhk.com\/wp-content\/uploads\/2020\/11\/\u87a2\u5e55\u622a\u5716-2020-11-02-\u4e0b\u534810.25.18.png 1384w\" sizes=\"(max-width: 1024px) 100vw, 1024px\" \/><\/figure>\n\n\n\n<figure class=\"wp-block-image size-large\"><img src=\"https:\/\/photolandhk.com\/wp-content\/uploads\/2020\/11\/New-Panasonic-L-mount-lenses-768x344-1.jpg\" alt=\"\" class=\"wp-image-10471\" srcset=\"https:\/\/photolandhk.com\/wp-content\/uploads\/2020\/11\/New-Panasonic-L-mount-lenses-768x344-1.jpg 768w, https:\/\/photolandhk.com\/wp-content\/uploads\/2020\/11\/New-Panasonic-L-mount-lenses-768x344-1-500x224.jpg 500w, https:\/\/photolandhk.com\/wp-content\/uploads\/2020\/11\/New-Panasonic-L-mount-lenses-768x344-1-600x269.jpg 600w\" sizes=\"(max-width: 768px) 100vw, 768px\" \/><\/figure>\n\n\n\n<p>\u4f86\u6e90\uff1a<a href=\"https:\/\/twitter.com\/nokishita_c\/status\/1323138888227979266\" class=\"ek-link\">Nokishita<\/a><\/p>\n","protected":false},"excerpt":{"rendered":"<p>Panasonic \u5373\u5c07\u5ba3\u5e03\u63a8\u51fa\u7528\u65bc L \u63a5\u53e3\u7684\u65b0\u578b 85mm f\/1.8 S \u93e1\u982d\uff0c\u7e7c\u7e8c\u64f4\u5927 L \u93e1\u93e1\u7fa4\u3002\u64da &hellip;<\/p>\n","protected":false},"author":7,"featured_media":10470,"comment_status":"open","ping_status":"open","sticky":false,"template":"","format":"standard","meta":{"_editorskit_title_hidden":false,"_editorskit_reading_time":1,"ub_ctt_via":"","spay_email":""},"categories":[6,679],"tags":[592,518,537,1466],"featured_image_src":"https:\/\/photolandhk.com\/wp-content\/uploads\/2020\/11\/Panasonic-85mm-f1.8-S-lens-for-L-mount.jpg","author_info":{"display_name":"Perry Yu","author_link":"https:\/\/photolandhk.com\/author\/perryyu\/"},"jetpack_featured_media_url":"https:\/\/photolandhk.com\/wp-content\/uploads\/2020\/11\/Panasonic-85mm-f1.8-S-lens-for-L-mount.jpg","_links":{"self":[{"href":"https:\/\/photolandhk.com\/wp-json\/wp\/v2\/posts\/10464"}],"collection":[{"href":"https:\/\/photolandhk.com\/wp-json\/wp\/v2\/posts"}],"about":[{"href":"https:\/\/photolandhk.com\/wp-json\/wp\/v2\/types\/post"}],"author":[{"embeddable":true,"href":"https:\/\/photolandhk.com\/wp-json\/wp\/v2\/users\/7"}],"replies":[{"embeddable":true,"href":"https:\/\/photolandhk.com\/wp-json\/wp\/v2\/comments?post=10464"}],"version-history":[{"count":0,"href":"https:\/\/photolandhk.com\/wp-json\/wp\/v2\/posts\/10464\/revisions"}],"wp:featuredmedia":[{"embeddable":true,"href":"https:\/\/photolandhk.com\/wp-json\/wp\/v2\/media\/10470"}],"wp:attachment":[{"href":"https:\/\/photolandhk.com\/wp-json\/wp\/v2\/media?parent=10464"}],"wp:term":[{"taxonomy":"category","embeddable":true,"href":"https:\/\/photolandhk.com\/wp-json\/wp\/v2\/categories?post=10464"},{"taxonomy":"post_tag","embeddable":true,"href":"https:\/\/photolandhk.com\/wp-json\/wp\/v2\/tags?post=10464"}],"curies":[{"name":"wp","href":"https:\/\/api.w.org\/{rel}","templated":true}]}}]"""
//        println(JSONObject(Json.decodeFromString<Project>(listOfPosts)))
//        println(Json.parse(ListSerializer(Post.serializer()), listOfPosts))
//        println(Json.decodeFromString<MainActivity.Project>(listOfPosts))
    }



}