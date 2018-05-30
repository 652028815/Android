package com.rookie.news.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.ArrayMap
import android.util.Log
import android.util.SparseArray
import com.rookie.news.R
import com.rookie.news.base.BaseActivity
import com.rookie.news.base.LazyFragment
import com.rookie.news.pojo.bean.NetWorkState
import com.rookie.news.pojo.response.Category
import com.rookie.news.ui.main.HotNewsFragment
import com.rookie.news.ui.main.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.getCategories().observe(this, Observer {
            val list = ArrayList<Category>(it)
            list.add(0, Category("热门", "hot"))
            initViewPager(list)
        })
    }

    private fun initViewPager(list: ArrayList<Category>) {
        val fragments = ArrayList<Fragment>()
        list.forEach {
            if (TextUtils.equals("hot", it.alias))
                fragments.add(HotNewsFragment.newInstance())
            else
                fragments.add(NewsFragment.newInstance(it))
        }
        view_pager.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return fragments[position]
            }

            override fun getCount(): Int {
                return list.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return list[position].name
            }
        }
        tab_layout.setupWithViewPager(view_pager)
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            private var selectedPage = 0
            private var lastSelected = 0
            override fun onPageScrollStateChanged(state: Int) {
                if (state == 0 && lastSelected != selectedPage) {
                    val fragment = fragments[selectedPage]
                    if (fragment is LazyFragment) {
                        lastSelected = selectedPage
                        fragment.lazyLoadData()
                    }
                }
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                selectedPage = position
            }
        })
        val fragment = fragments[0]
        if (fragment is LazyFragment) {
            fragment.lazyLoadData()
        }
    }
}
