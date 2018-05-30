package com.rookie.news

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * Author: FK
 * Date：  2018/5/29.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}
