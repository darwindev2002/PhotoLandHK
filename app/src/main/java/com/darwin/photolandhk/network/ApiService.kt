package com.darwin.photolandhk.network

import com.darwin.photolandhk.posts.Category
import com.darwin.photolandhk.posts.CategoryCount
import com.darwin.photolandhk.posts.PostContent
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

enum class ApiFilter(val value: String){
    SHOW_RENT("rent"),
    SHOW_BUY("buy"),
    SHOW_ALL("all")
}

private val BASE_URL = "https://photolandhk.com/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
//    .add(Date::class.java, Rfc3339DateJsonAdapter())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


/**
 * A public interface that exposes the [getCategories], [getPostList], [getPost] methods
 */
interface ApiService {

    @GET("wp-json/wp/v2/categories")
    suspend fun getCategories(@Query("per_page") pages: Int = 100,
                              @Query("_fields") fields: String = "id,name,count"): List<Category>

    @GET("wp-json/wp/v2/categories/{category}?_fields=count")
    suspend fun getCategoryPostCount(@Path("category") id: Int): CategoryCount

    @GET("wp-json/wp/v2/posts")
    suspend fun getPostList(@Query("per_page") pages: Int = 30,
                            @Query("page") page: Int = 1,
                            @Query("categories") category: Int,
                            @Query("_fields") fields: String = "id,date,modified,title,content,author,categories,tags,author_info,featured_media,featured_image_src"): List<PostContent>

//    @GET("wp-json/wp/v2/posts/{id}")
//    suspend fun getPost(@Path("id") id: Int,
//                        @Query("_fields") fields: String = "id,date,modified,title,content,author,categories,tags,author_info"): PostContent

}

val categoryList = mapOf("Uncategorized" to 1,
    "攝影分享" to 112,
    "攝影大事報" to 6,
    "攝影教學" to 111,
    "攝影齊齊傾" to 108,
    "新機傳聞" to 679,
    "新機發佈" to 682,
    "新鏡傳聞" to 680,
    "新鏡發佈" to 681,
    "活動報名站" to 109,
    "濾鏡報告" to 176,
    "焦點文章" to 821,
    "產品介紹" to 169,
    "相機報告" to 757,
    "相機袋報告" to 170,
    "真．用家報告" to 166,
    "穩定器報告" to 175,
    "腳架報告" to 173,
    "航拍機報告" to 174,
    "配件報告" to 172,
    "鏡頭報告" to 171)

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object Api {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

enum class ApiStatus {LOADING, ERROR, DONE}

val homeOverviewListSize = 8
val LOAD_PER_TIME = 30