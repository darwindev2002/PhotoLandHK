package com.darwin.photolandhk.posts

import android.os.StrictMode
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

object PostProcessing {

    const val CATEGORY_LIST = 98
    const val POST_OVERVIEW_LIST = 99
    const val POST_CONTENT = 100
    const val ITEM_COUNT = 8

    data class Category(val id: Int, val count: Int)
    val category_map = getCategories()

    fun getCategories(): MutableMap<String, Category> {
        val post_list = getJson(CATEGORY_LIST)
        val list = JSONArray(post_list)
        val category_map: MutableMap<String, Category> = mutableMapOf()
        for (i in 0 until list.length()){
            val temp = list.getJSONObject(i)
            category_map[temp.optString("name")] =
                Category(temp.optInt("id"), temp.optInt("count"))
        }
        return category_map
    }

    fun getPostOverview(category: Int?, count: Int=ITEM_COUNT): JSONArray {
        val post_list = getJson(POST_OVERVIEW_LIST, category=category, count=count)
        val list = JSONArray(post_list)
        return list
    }

    fun getPostClass(id: Int): PostContentHolder.Post {
        val content_str = getJson(POST_CONTENT, id=id)
        val obj = Json.decodeFromString<PostContentHolder.Post>(content_str.substring(1, content_str.length-1))
        return obj
    }

    private fun getJson(option: Int, id: Int=0, category: Int?=0, count: Int=ITEM_COUNT): String {

        val policy: StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        var jsonString = "There's some error"
        val urlConnection = when (option){
            CATEGORY_LIST -> URL("https://photolandhk.com/wp-json/wp/v2/categories?per_page=100&_fields=id,name,count").openConnection() as HttpURLConnection
            POST_OVERVIEW_LIST -> URL("https://photolandhk.com/wp-json/wp/v2/posts?per_page=$count&categories=$category&_fields=id,title,featured_media,featured_image_src").openConnection() as HttpURLConnection
            POST_CONTENT -> URL("https://photolandhk.com/wp-json/wp/v2/posts?include=$id&_fields=date,modified,title,content,author,featured_media,categories,tags,featured_image_src,author_info").openConnection() as HttpURLConnection
            else -> null
        }
        println(urlConnection)
        urlConnection ?: return "Error occurred."
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