package com.darwin.photolandhk.ui.post_fragment_pkg

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import com.darwin.photolandhk.MainActivity
import com.darwin.photolandhk.R
import com.darwin.photolandhk.posts.PostProcessing

class PostActivity : AppCompatActivity() {

    val items: ArrayList<PostItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        val toolbar: Toolbar = findViewById(R.id.postToolbar)
        setSupportActionBar(toolbar)
        toolbar.navigationIcon =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_action_back, null)
        toolbar.setNavigationOnClickListener{
            it.context.startActivity(Intent(it.context, MainActivity::class.java))
        }

        val intent: Intent = intent
        if (intent.extras != null){
            val post_id = intent.getIntExtra("post_id", 0)
        }

        val mTextView: TextView = findViewById(R.id.textView)
        var str = PostProcessing.getJson(10502)
        mTextView.text = str
    }

}