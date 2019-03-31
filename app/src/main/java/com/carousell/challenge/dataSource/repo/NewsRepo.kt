package com.carousell.challenge.dataSource.repo

import com.carousell.challenge.dataSource.local.NewsDao
import com.carousell.challenge.dataSource.model.NewsModel
import com.carousell.challenge.dataSource.remote.NewsApiService
import io.reactivex.Completable
import io.reactivex.Single
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject


interface NewsRepo {
    fun getNews(): Single<List<NewsModel>>
    fun fetchNews(): Completable
}

class NewsRepoImpl : KoinComponent, NewsRepo {
    private val remoteDataSource by inject<NewsApiService>()
    private val localDataSource by inject<NewsDao>()

    override fun getNews(): Single<List<NewsModel>> {
        return localDataSource.getNews()

    }

    override fun fetchNews(): Completable {
        return remoteDataSource.fetchNews().flatMapCompletable { news ->
            Completable.fromAction { localDataSource.insertNews(news) }
        }
    }


}
