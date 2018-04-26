package com.rookie.demo.pojo.response

import com.rookie.demo.pojo.bean.BaseResponse
import com.rookie.demo.pojo.bean.ThemeItem

/**
 * Author: FK
 * Date：  2018/4/24.
 */
class GetAllThemesResponse : BaseResponse() {
    var others: List<ThemeItem>? = null
}