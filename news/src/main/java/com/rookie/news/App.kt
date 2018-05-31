package com.rookie.news

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import java.util.concurrent.Executors

/**
 * Author: FK
 * Date：  2018/5/29.
 */
class App : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        val networkThread = Executors.newFixedThreadPool(1)
    }

    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext
    }
}
