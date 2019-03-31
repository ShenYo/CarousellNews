package com.carousell.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.carousell.base.BaseRecyclerViewAdapter
import com.carousell.base.BaseRecyclerViewHolder
import com.carousell.imageloader.GlideApp
import kotlinx.android.synthetic.main.item_news.view.*
import org.koin.standalone.inject

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

    private val viewModel by inject<NewsViewModel>()

    override fun bindDataToView(data: NewsModel) {
        bindToBanner(data.bannerUrl)
        bindToTitle(data.title)
        bindToDescription(data.description)
        bindToCreateTime(data.timeCreated)
    }

    private fun bindToBanner(bannerUrl: String) {
        GlideApp.with(itemView.context)
            .load(bannerUrl)
            .error(R.drawable.ic_launcher_background)
            .into(itemView.banner)
    }

    private fun bindToTitle(title: String) {
        itemView.textTitle.text = title
    }

    private fun bindToDescription(description: String) {
        itemView.textDescription.text = description
    }

    private fun bindToCreateTime(timeCreated: Long) {
        itemView.textCreatedAt.text = viewModel.getTextCreatedAt(timeCreated)

    }

}
