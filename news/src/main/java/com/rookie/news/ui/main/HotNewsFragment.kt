package com.rookie.news.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rookie.news.R
import com.rookie.news.base.BaseFragment
import com.rookie.news.pojo.bean.NetWorkState
import com.rookie.news.pojo.response.News
import com.rookie.news.ui.MainViewModel
import kotlinx.android.synthetic.main.fragment_news_hot.*

/**
 * Author: FK
 * Date：  2018/5/28.
 */
class HotNewsFragment : BaseFragment() {
    companion object {
        fun newInstance(): HotNewsFragment {
            return HotNewsFragment()
        }
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: NewsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news_hot, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        adapter = NewsAdapter(emptyList<News>().toMutableList())
        recycler_view.adapter = adapter

        viewModel.getHotNews().observe(this, Observer {
            it?.news?.let {
                adapter.refresh(it)
            }
        })
    }

    fun refresh() {
        viewModel.getHotNews()
    }
}