package com.rookie.news.pojo.response

/**
 * Author: FK
 * Date：  2018/5/28.
 */

data class NewsResponse(
    val count: Int,
    val first_id: String,
    val last_id: String,
    val news: List<News>
)

data class News(
    val news_id: String,
    val title: String,
    val source: String,
    val gmt_publish: Long,
    val hot_index: Int,
    val selection: Boolean,
    val category: List<String>,
    val thumbnail_img: List<String>,
    val url: String,
    val summary_create_time: Any,
    val summary_update_time: Any,
    val summary: Any,
    val content: Any
)