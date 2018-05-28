package com.rookie.demo.repository

import android.arch.paging.DataSource
import com.rookie.demo.pojo.bean.LastThemeStory
import com.rookie.demo.pojo.response.GetLastThemeResponse

/**
 * Author: FK
 * Date：  2018/5/17.
 */
class HotNewsDataSourceFactory : DataSource.Factory<GetLastThemeResponse, LastThemeStory>() {
    override fun create(): DataSource<GetLastThemeResponse, LastThemeStory> {
        return HotNewsPageKeyedDataSource()
    }
}