package com.rookie.news.pojo.response

/**
 * Author: FK
 * Date：  2018/5/25.
 */
data class Response<T>(
        val success: Boolean,
        val code: Any,
        val msg: String,
        val requestId: String,
        val data: T
)