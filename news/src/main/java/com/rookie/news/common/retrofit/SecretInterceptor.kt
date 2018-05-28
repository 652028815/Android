package com.rookie.news.common.retrofit

import android.util.Log
import com.rookie.news.common.constant.UrlConstant
import com.rookie.news.common.secret.Md5
import okhttp3.Interceptor
import okhttp3.Response


/**
 * Author: FK
 * Date：  2018/5/25.
 */
class SecretInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()

        val timeStamp = System.currentTimeMillis()
        val secret = Md5.encode("${UrlConstant.SecretKey}$timeStamp${UrlConstant.AccessKey}")
        // 添加新的参数
        val authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host())
                .addQueryParameter("access_key", UrlConstant.AccessKey)
                .addQueryParameter("timestamp", "$timeStamp")
                .addQueryParameter("signature", secret)

        // 新的请求
        val newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(authorizedUrlBuilder.build())
                .build()
        return chain.proceed(newRequest)
    }
}