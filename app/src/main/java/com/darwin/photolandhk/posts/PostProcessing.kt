package com.darwin.photolandhk.posts

import android.os.StrictMode
import java.net.HttpURLConnection
import java.net.URL

object PostProcessing {
    fun getJson(id: Int): String {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            val policy: StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        var jsonString:String = "There's some error"
        // https://photolandhk.com/wp-json/wp/v2/posts?p=10502
        // https://photolandhk.com/wp-json/wp/v2/posts?per_page=1&page=2
        val urlConnection = URL("https://photolandhk.com/wp-json/wp/v2/posts?p=10502").openConnection() as HttpURLConnection
        try {
            urlConnection.connect()
            jsonString = urlConnection.inputStream.bufferedReader().readText()
        } catch (e: Exception){
            e.printStackTrace()
        } finally {
            urlConnection.disconnect()
        }
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