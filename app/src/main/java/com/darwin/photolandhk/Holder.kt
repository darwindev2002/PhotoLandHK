package com.darwin.photolandhk

import kotlinx.serialization.Serializable

class Holder {

    @Serializable
    data class Post(
        val author: Int,  // 7
        val author_info: AuthorInfo,  // {display_name: "Perry Yu", author_link: "https://photolandhk.com/author/perryyu/"}
        val categories: List<Int>,  // [6]
        val comment_status: String,  // "open"
        val content: Content,  // {false,…}
        val date: String,  // "2020-11-05T12:21:00"
        val date_gmt: String,  // "2020-11-05T04:21:00"
        val excerpt: Excerpt,  // {rendered: "<p>ON1 發布了最新版的照片編輯軟件 Photo RAW 2021，其中包括新的整合工具，ON1 Portrai &hellip;</p>↵",…}
        val featured_image_src: String,  // "https://photolandhk.com/wp-content/uploads/2020/11/16-9-3-scaled.jpg"
        val featured_media: Int,  // 10503
        val format: String,  //"standard"
        val guid: Guid,  // {rendered: "https://photolandhk.com/?p=10502"}
        val id: Int, // 10502
        val jetpack_featured_media_url: String,  // "https://photolandhk.com/wp-content/uploads/2020/11/16-9-3-scaled.jpg"
        val link: String,  // "https://photolandhk.com/on1-photo-raw-2021-%e5%b7%b2%e5%ae%8c%e5%85%a8%e6%95%b4%e5%90%88-portrait-ai-%e5%8a%9f%e8%83%bd/"
        val meta: Meta,  // {_editorskit_title_hidden: false, _editorskit_reading_time: 2, ub_ctt_via: "", spay_email: ""}
        val modified: String,  // "2020-11-05T12:22:12"
        val modified_gmt: String,  // "2020-11-05T04:22:12"
        val ping_status: String,  // "open"
        val slug: String,  // "on1-photo-raw-2021-%e5%b7%b2%e5%ae%8c%e5%85%a8%e6%95%b4%e5%90%88-portrait-ai-%e5%8a%9f%e8%83%bd"
        val status: String, // "publish"
        val sticky: Boolean, //false
        val tags: List<Int>,  // [522, 991, 994, 534, 986, 990, 523, 1477]
        val template: String,  // ""
        val title: PostTitle, // {rendered: "ON1 Photo RAW 2021 已完全整合 Portrait AI 功能"}
        val type: String  // "post"
    )

    @Serializable
    data class AuthorInfo(
        // {display_name: "Perry Yu", author_link: "https://photolandhk.com/author/perryyu/"}
        val display_name: String,  // "Perry Yu"
        val author_link: String  // "https://photolandhk.com/author/perryyu/"
    )

    @Serializable
    data class Content(
        // {false,…}
        val protected: Boolean,  // false
        val rendered: String  // "..."
    )

    @Serializable
    data class Excerpt(
        // {rendered: "<p>ON1 發布了最新版的照片編輯軟件 Photo RAW 2021，其中包括新的整合工具，ON1 Portrai &hellip;</p>↵",…}
        val rendered: String,  // "<p>ON1 發布了最新版的照片編輯軟件 Photo RAW 2021，其中包括新的整合工具，ON1 Portrai &hellip;</p>↵"
        val protected: Boolean  // false
    )

    @Serializable
    data class Guid(
        // {rendered: "https://photolandhk.com/?p=10502"}
        val rendered: String  // "https://photolandhk.com/?p=10502"
    )

    @Serializable
    data class Meta(
        // {_editorskit_title_hidden: false, _editorskit_reading_time: 2, ub_ctt_via: "", spay_email: ""}
        val _editorskit_title_hidden: Boolean,  // false
        val _editorskit_reading_time: Int,  // 2
        val ub_ctt_via: String,  // ""
        val spay_email: String  // ""
    )

    @Serializable
    data class PostTitle(
        // {rendered: "ON1 Photo RAW 2021 已完全整合 Portrait AI 功能"}
        val rendered: String  // "ON1 Photo RAW 2021 已完全整合 Portrait AI 功能"
    )
}