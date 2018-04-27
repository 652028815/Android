package com.rookie.demo.ui.home.hotnews

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rookie.demo.R
import com.rookie.demo.base.BaseFragment
import com.rookie.demo.pojo.bean.NetWorkState
import kotlinx.android.synthetic.main.fragment_main_hotnews.*

/**
 * Author: FK
 * Date：  2018/4/25.
 */
class HotNewsFragment : BaseFragment() {
    companion object {
        fun newInstance() = HotNewsFragment()
    }

    private lateinit var adapter: HotNewsAdapter
    private lateinit var viewModel: HotNewsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_hotnews, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HotNewsViewModel::class.java)
        initView()
        loadData()
    }

    private fun loadData() {
        viewModel.getLastTheme().observe(this, Observer { it?.let { adapter.notifyDataSetChanged(it) } })
    }

    private fun initView() {
        setTitle("今日热闻")
        adapter = HotNewsAdapter()
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = adapter
        refresh_Layout.setOnRefreshListener { loadData() }
        viewModel.netWorkState.observe(this, Observer { refresh_Layout.isRefreshing = it == NetWorkState.LOADING })
    }
}