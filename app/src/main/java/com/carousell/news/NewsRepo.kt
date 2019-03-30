package com.carousell.news

import com.carousell.base.ResourceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject


class NewsRepo : KoinComponent {
    val resourceManager by inject<ResourceManager>()

    fun fetchNews(): Single<List<NewsModel>> {
        val listType = object : TypeToken<List<NewsModel>>() {}.type

        return Single.just(
                Gson().fromJson(resourceManager.loadJSONFromAsset("news.json"), listType)
        )
    }

}
