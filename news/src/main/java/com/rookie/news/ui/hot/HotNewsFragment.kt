package com.rookie.news.ui.hot

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rookie.news.R
import com.rookie.news.base.LazyFragment
import com.rookie.news.pojo.bean.NetworkState
import com.rookie.news.pojo.response.News
import com.rookie.news.repository.hot.HotNewsRepository
import com.rookie.news.ui.main.NewsAdapter
import kotlinx.android.synthetic.main.fragment_news_hot.*

/**
 * Author: FK
 * Date：  2018/5/28.
 */
class HotNewsFragment : LazyFragment() {
    companion object {
        fun newInstance(): HotNewsFragment {
            return HotNewsFragment()
        }
    }

    private lateinit var viewModel: HotNewsViewModel
    private lateinit var adapter: NewsAdapter
    private var hasLoad = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news_hot, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
        if (hasLoad)
            loadNews()
    }

    private fun loadNews() {
        viewModel.lazyLoadData()
    }

    private fun initData() {
        viewModel = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return HotNewsViewModel(HotNewsRepository()) as T
            }
        })[HotNewsViewModel::class.java]
        viewModel.news.observe(this, Observer<PagedList<News>> {
            adapter.submitList(it)
        })
    }

    private fun initView() {
        adapter = NewsAdapter()
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(context)

        refresh_layout.setOnRefreshListener { viewModel.refresh() }

        viewModel.refreshState.observe(this, Observer {
            refresh_layout.isRefreshing = it == NetworkState.LOADING
        })
    }

    override fun lazyLoadData() {
        if (!isResumed) {
            hasLoad = true
            return
        }

        loadNews()
    }
}