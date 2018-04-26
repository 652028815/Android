package com.rookie.demo.ui.home.other

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rookie.demo.R
import com.rookie.demo.base.BaseFragment
import com.rookie.demo.pojo.bean.ThemeItem

/**
 * Author: FK
 * Date：  2018/4/25.
 */
class OtherThemeFragment : BaseFragment() {

    companion object {
        fun newInstance(item: ThemeItem): OtherThemeFragment {
            val fragment = OtherThemeFragment()
            val bundle = Bundle()
            bundle.putParcelable("item", item)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_other, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(OtherThemeViewModel::class.java)
    }
}