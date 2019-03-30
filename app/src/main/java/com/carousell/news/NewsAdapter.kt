package com.carousell.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.carousell.base.BaseRecyclerViewAdapter
import com.carousell.base.BaseRecyclerViewHolder
import com.carousell.imageloader.GlideApp
import kotlinx.android.synthetic.main.item_news.view.*
import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.Period
import org.threeten.bp.ZoneId

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
        val period = Period.between(
            Instant.ofEpochSecond(timeCreated).atZone(ZoneId.systemDefault()).toLocalDate(),
            LocalDate.now()
        )

        itemView.textCreatedAt.text = when {
            period.years > 0 -> {
                "${period.years} years ago"
            }
            period.months > 0 -> {
                "${period.months} months ago"
            }
            period.days / 7 >= 4 -> {
                "${period.days / 7} weeks ago"
            }
            else -> {
                "${period.days} weeks ago"
            }
        }

    }

}
