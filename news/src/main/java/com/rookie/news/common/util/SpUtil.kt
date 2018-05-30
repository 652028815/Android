package com.rookie.news.common.util

import android.content.Context
import android.content.SharedPreferences
import com.rookie.news.App

/**
 * Author: FK
 * Date：  2018/5/29.
 */
class SpUtil {
    companion object {
        fun put(call: (SharedPreferences.Editor) -> Unit) {
            val sp = App.context.getSharedPreferences("SpUtil", Context.MODE_PRIVATE)
            val editor = sp.edit()
            call.invoke(editor)
            editor.apply()
        }

        fun getSp() = App.context.getSharedPreferences("SpUtil", Context.MODE_PRIVATE)
    }
}