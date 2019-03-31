package com.carousell.dataSource.repo

import com.carousell.challenge.dataSource.repo.NewsRepo
import com.carousell.challenge.dataSource.model.NewsModel
import com.carousell.challenge.util.ResourceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Completable
import io.reactivex.Single
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

/**
 * Description:
 *
 * @author Shenyo
 * @version 1.0, 2019/3/31
 */


class MockNewsRepo : NewsRepo, KoinComponent {
    private val gson by inject<Gson>()
    private val resourceManager by inject<ResourceManager>()
    private val listType = object : TypeToken<List<NewsModel>>() {}.type!!

    override fun getNews(): Single<List<NewsModel>> {
        return Single.just(gson.fromJson(resourceManager.loadJSONFromAsset("news.json"), listType))
    }

    override fun fetchNews(): Completable {
        return Completable.complete()
    }

}