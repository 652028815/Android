package com.rookie.demo.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.RadioButton
import com.rookie.demo.R
import com.rookie.demo.pojo.bean.ThemeItem
import com.rookie.demo.pojo.response.GetAllThemesResponse
import com.rookie.demo.ui.home.hotnews.HotNewsFragment
import com.rookie.demo.ui.home.other.OtherThemeFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_main_content.*
import kotlinx.android.synthetic.main.layout_main_navigation.*
import kotlinx.android.synthetic.main.layout_main_navigation.view.*

class MainActivity : AppCompatActivity() {
    var themes: List<ThemeItem>? = null
    var theme: ThemeItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initActionBar()
        initNavigation()
        initContent()
        loadData()
    }

    private fun initActionBar() {
        setSupportActionBar(toolbar)
        val close = R.string.navigation_drawer_close
        val open = R.string.navigation_drawer_open
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, open, close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun initNavigation() {
        //TODO:这里为啥不能直接用re_menu，下面却能直接用
        navigation.getHeaderView(0).rg_menu.setOnCheckedChangeListener { group, _ ->
            val index = group.indexOfChild(group.focusedChild)
            theme = if (index != 0) themes?.get(index - 1) else null
            updateFragment()
            drawer.closeDrawer(Gravity.START)
        }
    }

    private fun initContent() {
        updateFragment()
    }

    private fun updateFragment() {
        val tag = "content_fragment"
        val fragment: Fragment
        if (theme != null) {
            fragment = OtherThemeFragment.newInstance(theme!!)
        } else {
            fragment = HotNewsFragment.newInstance()
        }
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, fragment, tag)
                .commitAllowingStateLoss()
    }

    private fun loadData() {
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getAllThemes().observe(this, Observer<GetAllThemesResponse> {
            it?.others?.forEach { item ->
                val rb: RadioButton = LayoutInflater.from(this).inflate(R.layout.include_main_menu,
                        rg_menu, false) as RadioButton
                rb.text = item.name
                rg_menu.addView(rb)
            }
        })
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(Gravity.START)) {
            drawer.closeDrawer(Gravity.START)
        } else {
            super.onBackPressed()
        }
    }
}
