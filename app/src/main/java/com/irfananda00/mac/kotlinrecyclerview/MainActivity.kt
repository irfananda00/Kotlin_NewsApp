package com.irfananda00.mac.kotlinrecyclerview

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mNews: ArrayList<Model.TopHeadlines.Article>
    private val mContext = this
    private lateinit var mAdapter: ListAdapter

    override fun onCreate(
        savedInstanceState: Bundle?
    ){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list_recycler_view.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false)
            // set the custom adapter to the RecyclerView
            mNews = ArrayList()
            mAdapter = ListAdapter(mNews, { article : Model.TopHeadlines.Article -> newsClicked(article) })
            adapter = mAdapter
            val helper = LinearSnapHelper()
            helper.attachToRecyclerView(list_recycler_view)
        }

        searchTopHeadlines("us")
    }

    private fun newsClicked(article: Model.TopHeadlines.Article){
        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(resources.getColor(R.color.colorPrimary))
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(article.url))
    }

    private fun showResult(
        result: List<Model.TopHeadlines.Article>,
        status: String,
        totalResults: Int
    ){
        Log.i("irfananda","result "+status+" total: "+totalResults)
        mNews.addAll(result)
        mAdapter.notifyDataSetChanged()
    }

    private fun searchTopHeadlines(
        mCountry: String
    ){
        Log.i("irfananda","searchTopHeadlines")
        NewsAPIService.disposable = NewsAPIService.newsAPIserve.getTopHeadLines(mCountry,"c5a413b844d64762b60e124618c0eddd")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {result -> showResult(result.articles, result.status, result.totalResults)},
                {error -> Log.e("irfananda",error.message)}
            )
    }

}
