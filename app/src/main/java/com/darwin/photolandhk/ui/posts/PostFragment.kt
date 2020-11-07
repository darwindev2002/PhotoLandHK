package com.darwin.photolandhk.ui.posts

import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.darwin.photolandhk.R
import java.net.HttpURLConnection
import java.net.URL

class PostFragment() : Fragment() {

    val items: ArrayList<PostItem> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val urlTemp = "https://www.google.com"
        createWebViewer(view, urlTemp)
//        suspend fun main() = coroutineScope {
//            delay(1000)
//            println(getJson())
//        }
        val mTextView: TextView = view.findViewById(R.id.textView)
        var str = getJson()
        mTextView.text = str
        println(str)
//        println("\u6211\u662f\u0044\u0061\u0072\u0077\u0069\u006e")

    }

    fun createWebViewer(view: View, url: String) {
//        val webView: WebView = view.findViewById(R.id.web)
//        webView.loadUrl(url)
    }

    fun getJson(): String {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            val policy: StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        var jsonString:String = "There's some error"
        val urlConnection = URL("https://photolandhk.com/wp-json/wp/v2/posts?page=2").openConnection() as HttpURLConnection
        try {
            urlConnection.connect()
            jsonString = urlConnection.inputStream.bufferedReader().readText()
        } catch (e: Exception){
            e.printStackTrace()
        } finally {
            urlConnection.disconnect()
        }
//        return json.dumps(jsonString, ).decode()
        return regexChinCharProcessing(jsonString)
    }

    fun regexChinCharProcessing(str: String) : String {
        val re = Regex("""(\\u[-0-9a-f]{4})|(\\/)""")
        return re.replace(str, transform={match:MatchResult -> toChinChar(match)})
    }

    fun toChinChar(match: MatchResult) : String {
        if (match.value=="""\/""") return "/"
        return match.value.substring(2,6).toInt(16).toChar().toString()
    }



}