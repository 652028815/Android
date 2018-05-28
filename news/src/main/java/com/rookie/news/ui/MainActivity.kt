package com.rookie.news.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.util.ArrayMap
import com.rookie.news.R
import com.rookie.news.pojo.bean.NetWorkState
import com.rookie.news.pojo.response.Category
import com.rookie.news.ui.main.HotNewsFragment
import com.rookie.news.ui.main.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val map = ArrayMap<String, Fragment>()
        map["热门"] = HotNewsFragment.newInstance()
        map["精选"] = HotNewsFragment.newInstance()
        initViewPager(map)

        viewModel.netWorkState.observe(this, Observer {
            refresh_layout.isRefreshing = it == NetWorkState.LOADING
        })
        refresh_layout.setOnRefreshListener {
            val fragment = map.valueAt(view_pager.currentItem)
            if (fragment is HotNewsFragment) {
                fragment.refresh()
            }
        }
    }

    private fun initViewPager(map: ArrayMap<String, Fragment>) {
        view_pager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return map.valueAt(position)
            }

            override fun getCount(): Int {
                return map.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return map.keyAt(position)
            }
        }
        tab_layout.setupWithViewPager(view_pager)
    }
}
