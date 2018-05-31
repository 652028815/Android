package com.rookie.news.ui.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.webkit.WebChromeClient
import com.rookie.news.R
import com.rookie.news.base.BaseActivity
import com.rookie.news.pojo.response.News
import kotlinx.android.synthetic.main.activity_web_view.*
import android.webkit.WebView
import android.webkit.WebViewClient

class WebViewActivity : BaseActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val news = intent.getParcelableExtra<News>("news")

        web_view.settings.javaScriptEnabled = true
        web_view.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                progress_bar.progress = newProgress
                progress_bar.visibility = if (newProgress == 100) View.GONE else View.VISIBLE
            }
        }

        web_view.webViewClient = object : WebViewClient() {

        }
        web_view.loadUrl(news?.url)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }
}