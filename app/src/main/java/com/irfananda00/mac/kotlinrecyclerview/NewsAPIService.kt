package com.irfananda00.mac.kotlinrecyclerview

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {

    @GET("top-headlines")
    fun getTopHeadLines(
        @Query("country")country: String,
        @Query("apiKey")apiKey: String):
            Observable<Model.TopHeadlines>

    companion object{
        fun create(): NewsAPIService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://newsapi.org/v2/")
                .build()

            return retrofit.create(NewsAPIService::class.java)
        }

        val newsAPIserve by lazy {
            create()
        }

        var disposable: Disposable? = null

    }
}