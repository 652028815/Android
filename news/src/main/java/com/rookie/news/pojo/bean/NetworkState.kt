package com.rookie.news.pojo.bean

/**
 * Author: FK
 * Date：  2018/4/24.
 */
class NetworkState(var msg: String?) {

    companion object {
        val LOADING = NetworkState(null)
        val SUCCESS = NetworkState(null)
        fun error(msg: String?) = NetworkState(msg)
    }
}