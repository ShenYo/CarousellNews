package com.carousell.news

import io.reactivex.Single

class NewsRepo {
    fun fetchNews(): Single<List<NewsModel>> {
        return Single.just(emptyList())
    }

}
