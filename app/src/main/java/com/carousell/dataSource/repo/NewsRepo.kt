package com.carousell.dataSource.repo

import com.carousell.dataSource.local.NewsDao
import com.carousell.dataSource.model.NewsModel
import com.carousell.dataSource.remote.NewsApiService
import com.carousell.util.ResourceManager
import com.google.gson.reflect.TypeToken
import io.reactivex.Completable
import io.reactivex.Single
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject


class NewsRepo : KoinComponent {
    val remoteDataSource by inject<NewsApiService>()
    val localDataSource by inject<NewsDao>()


    val resourceManager by inject<ResourceManager>()
    val listType = object : TypeToken<List<NewsModel>>() {}.type


    fun getNews(): Single<List<NewsModel>> {
        return localDataSource.getNews()

    }

    fun fetchNews(): Completable {
        return remoteDataSource.fetchNews().flatMapCompletable { news ->
            Completable.fromAction { localDataSource.insertNews(news) }
        }
    }


}
