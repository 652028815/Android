package com.rookie.news.api

import com.rookie.news.common.constant.UrlConstant
import com.rookie.news.common.retrofit.SecretInterceptor
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
                .addInterceptor(SecretInterceptor())
                .build()

        private val service = Retrofit.Builder()
                .baseUrl(UrlConstant.Base_Url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsService::class.java)

        fun getCategories() = service.getCategories()

        fun getAllNews(category: String, size: String, lastId: String?) =
                service.getAllNews(category, size, lastId)

        fun getHotNews(size: String, lastId: String?) = service.getHotNews(size, lastId)

        fun getSelectionNews() = service.getSelectionNews()
    }
}