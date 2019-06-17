package com.irfananda00.mac.kotlinrecyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)){

    private var mImage: ImageView? = null
    private var mTitle: TextView? = null
    private var mDate: TextView? = null
    private var mDesc: TextView? = null

    init {
        mImage = itemView.findViewById(R.id.img)
        mTitle = itemView.findViewById(R.id.title)
        mDate = itemView.findViewById(R.id.date)
        mDesc = itemView.findViewById(R.id.desc)
    }

    fun bind(model: Model.TopHeadlines.Article, clickListener: (Model.TopHeadlines.Article) -> Unit) {
        mTitle?.text = model.title
        mDate?.text = Helper.dateFormatter(model.publishedAt)
        mDesc?.text = model.description
        Picasso.get().load(model.urlToImage).into(mImage)
        itemView.setOnClickListener{
            clickListener(model)
        }
    }
}