package com.darwin.photolandhk.ui.post_fragment_pkg

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.HtmlCompat
import com.darwin.photolandhk.MainActivity
import com.darwin.photolandhk.R
import com.darwin.photolandhk.posts.ImageGetter
import com.darwin.photolandhk.posts.PostProcessing

class PostActivity : AppCompatActivity() {

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
        val post_id = intent.getIntExtra("post_id", 0)

        val mTextView: TextView = findViewById(R.id.textView)
        val post = PostProcessing.getPostClass(post_id)
        mTextView.text = Html.fromHtml(post.content.rendered, HtmlCompat.FROM_HTML_MODE_LEGACY, ImageGetter, null)
    }
}