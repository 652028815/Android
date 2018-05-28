package com.rookie.news.pojo.bean

/**
 * Author: FK
 * Date：  2018/4/24.
 */
class NetWorkState(var msg: String?) {

    companion object {
        val LOADING = NetWorkState(null)
        val SUCCESS = NetWorkState(null)
        fun error(msg: String?) = NetWorkState(msg)
    }
}