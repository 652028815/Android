package com.rookie.news.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rookie.news.R
import com.rookie.news.pojo.response.Category
import kotlinx.android.synthetic.main.fragment_main_news.*

/**
 * Author: FK
 * Date：  2018/5/25.
 */
class NewsFragment : Fragment() {
    companion object {
        fun newInstance(category: Category): NewsFragment {
            val fragment = NewsFragment()
            val bundle = Bundle()
            bundle.putParcelable("data", category)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val category = arguments?.getParcelable<Category>("data")
        category?.let {
            tv.text = it.name
        }
    }
}