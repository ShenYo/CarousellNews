package com.carousell.dataSource.repo

import com.carousell.base.ResourceManager
import com.carousell.dataSource.model.NewsModel
import com.carousell.dataSource.remote.NewsApiService
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject


class NewsRepo : KoinComponent {
    val remoteDataSource by inject<NewsApiService>()
    val resourceManager by inject<ResourceManager>()

    fun fetchNews(): Single<List<NewsModel>> {
        val listType = object : TypeToken<List<NewsModel>>() {}.type

        return remoteDataSource.fetchNews()
    }

}
