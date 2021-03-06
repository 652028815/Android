package com.rookie.demo.api

import com.rookie.demo.common.constant.UrlConstant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Author: FK
 * Date：  2018/4/24.
 */
class Api {
    companion object {
        private val client = OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

        private val service = Retrofit.Builder()
                .baseUrl(UrlConstant.Base_Url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ZhihuService::class.java)

        fun getStartInfo(width: Int, height: Int) = service.getStartInfo(width, height)

        fun getAllThemes() = service.getAllThemes()

        fun getLastTheme() = service.getLastTheme()

        fun getThemeResponse(id: Int) = service.getThemeResponse(id)
    }
}