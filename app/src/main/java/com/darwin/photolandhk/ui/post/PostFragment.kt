package com.darwin.photolandhk.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.darwin.photolandhk.databinding.FragmentPostBinding
import com.darwin.photolandhk.posts.PostContent

class PostFragment(post: PostContent) : Fragment() {

    private val _selectedPost = MutableLiveData<PostContent>()
    val selectedPost: LiveData<PostContent>
        get() = _selectedPost

    init {
        _selectedPost.value = post
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentPostBinding.inflate(inflater)
        binding.lifecycleOwner = this

//        val post = PostFragmentArgs.fromBundle(arguments!!).selectedPost

//        val viewModelFactory = PostViewModelFactory(post, application)
//        binding.viewModel = ViewModelProvider(this, viewModelFactory).get(PostViewModel::class.java)

        return super.onCreateView(inflater, container, savedInstanceState)
        TODO("Handle null pointer from arguments!!")
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.fragment_post)
//
//        val toolbar: Toolbar = findViewById(R.id.postToolbar)
//        setSupportActionBar(toolbar)
//        toolbar.navigationIcon =
//            ResourcesCompat.getDrawable(resources, R.drawable.ic_action_back, null)
//        toolbar.setNavigationOnClickListener{
//            it.context.startActivity(Intent(it.context, MainActivity::class.java))
//        }
//
//        val intent: Intent = intent
//        val post = intent.getSerializableExtra("post") as PostContent
//
//        val mTextView: TextView = findViewById(R.id.textView)
//        println(post.content.rendered)
//        mTextView.text = Html.fromHtml(post.content.rendered, HtmlCompat.FROM_HTML_MODE_LEGACY, ImageGetter, null)
//    }

    class Glid
}