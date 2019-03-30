package com.carousell.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.carousell.base.BaseRecyclerViewAdapter
import com.carousell.base.BaseRecyclerViewHolder
import com.carousell.imageloader.GlideApp
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(
     context: Context
) : BaseRecyclerViewAdapter<NewsModel>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        return NewsItemViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_news
    }

}

class NewsItemViewHolder(itemView: View) : BaseRecyclerViewHolder<NewsModel>(itemView) {

    override fun bindDataToView(data: NewsModel) {
        GlideApp.with(itemView.context)
            .load("https://storage.googleapis.com/carousell-interview-assets/android/images/carousell-siu-rui-ceo-tia-sg-2018.jpg")
            .error(R.drawable.ic_launcher_background)
            .into(itemView.banner)
    }


}
