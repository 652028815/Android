package com.rookie.news.ui.main

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
import android.widget.Toast
import com.rookie.news.R
import com.rookie.news.base.LazyFragment
import com.rookie.news.pojo.bean.NetworkState
import com.rookie.news.pojo.response.Category
import com.rookie.news.pojo.response.News
import com.rookie.news.repository.news.NewsRepository
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
    }

    private fun initData() {
        viewModel = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return NewsViewModel(NewsRepository()) as T
            }
        })[NewsViewModel::class.java]
        val category = arguments?.getParcelable<Category>("data")
        viewModel.category.postValue(category)

        viewModel.news.observe(this, Observer<PagedList<News>> {
            adapter.submitList(it)
        })
    }

    private fun initView() {
        adapter = NewsAdapter()
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = adapter

        viewModel.refreshState.observe(this, Observer {
            if (!it!!.msg.isNullOrEmpty()) {
                Toast.makeText(context, it.msg, Toast.LENGTH_SHORT).show()
            } else {
                refresh_layout.isRefreshing = it == NetworkState.LOADING
            }
        })
        viewModel.loadMoreState.observe(this, Observer {
            if (!it!!.msg.isNullOrEmpty()) {
                Toast.makeText(context, it.msg, Toast.LENGTH_SHORT).show()
            }
        })

        refresh_layout.setOnRefreshListener { viewModel.refresh() }
    }

    override fun lazyLoadData() {
        viewModel.lazyLoadData()
    }
}