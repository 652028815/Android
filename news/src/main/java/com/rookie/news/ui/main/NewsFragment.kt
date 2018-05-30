package com.rookie.news.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rookie.news.R
import com.rookie.news.base.BaseFragment
import com.rookie.news.base.LazyFragment
import com.rookie.news.pojo.bean.NetWorkState
import com.rookie.news.pojo.response.Category
import com.rookie.news.pojo.response.News
import kotlinx.android.synthetic.main.fragment_main_news.*

/**
 * Author: FK
 * Date：  2018/5/25.
 */
class NewsFragment : LazyFragment() {
    private val TAG = "NewsFragment"

    companion object {
        fun newInstance(category: Category): NewsFragment {
            val fragment = NewsFragment()
            val bundle = Bundle()
            bundle.putParcelable("data", category)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var viewModel: NewsViewModel
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        adapter = NewsAdapter(emptyList<News>().toMutableList())
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = adapter

        viewModel.netWorkState.observe(this, Observer {
            refresh_layout.isRefreshing = it == NetWorkState.LOADING
        })

        refresh_layout.setOnRefreshListener { viewModel.getNews() }
    }

    private fun initData() {
        viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        val category = arguments?.getParcelable<Category>("data")
        viewModel.category = category
    }

    override fun lazyLoadData() {
        viewModel.getNews().observe(this, Observer {
            it?.news?.let {
                adapter.refresh(it)
            }
        })
    }
}