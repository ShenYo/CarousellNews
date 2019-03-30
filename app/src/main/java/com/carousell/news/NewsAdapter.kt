package com.carousell.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.carousell.base.BaseRecyclerViewAdapter
import com.carousell.base.BaseRecyclerViewHolder

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
