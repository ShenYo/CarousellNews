package com.carousell.news

import androidx.lifecycle.ViewModel
import io.reactivex.Single
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class NewsViewModel : ViewModel(), KoinComponent {

    val newsRepo by inject<NewsRepo>()

    fun fetchNews() : Single<List<NewsModel>> {
        return newsRepo.fetchNews()
    }

}
