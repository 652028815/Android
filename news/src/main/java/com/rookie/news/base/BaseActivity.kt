package com.rookie.news.base

import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity

/**
 * Author: FK
 * Date：  2018/4/24.
 */
open class BaseActivity : AppCompatActivity() {
    protected val uiHandler: Handler = Handler(Looper.getMainLooper())
}