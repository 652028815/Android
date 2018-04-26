package com.rookie.demo.pojo.bean

import com.google.gson.annotations.SerializedName

/**
 * Author: FK
 * Date：  2018/4/24.
 */
open class BaseResponse {
    @SerializedName("status")
    var status: Int = 0
    @SerializedName("error_msg")
    var errorMsg: String? = null
}