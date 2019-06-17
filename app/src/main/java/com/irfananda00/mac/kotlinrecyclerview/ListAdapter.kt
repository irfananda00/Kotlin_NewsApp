package com.irfananda00.mac.kotlinrecyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class ListAdapter(
    val list: List<Model.TopHeadlines.Article>,
    val clickListener: (Model.TopHeadlines.Article) -> Unit): RecyclerView.Adapter<MovieViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val news: Model.TopHeadlines.Article = list[position]
        holder.bind(news,clickListener)
    }

}