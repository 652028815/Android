package com.rookie.demo.ui.start

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import com.rookie.demo.R
import com.rookie.demo.base.BaseActivity
import com.rookie.demo.common.glide.GlideApp
import com.rookie.demo.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val viewModel = ViewModelProviders.of(this).get(StartViewModel::class.java)
        viewModel.getStartInfo().observe(this, Observer { response ->
            GlideApp.with(this)
                    .load(response?.img)
                    .into(iv)
        })

        uiHandler.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1000)
    }
}
