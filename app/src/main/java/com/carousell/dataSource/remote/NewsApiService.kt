package com.carousell.dataSource.remote

import com.carousell.dataSource.model.NewsModel
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Description:
 *
 * @author Shenyo
 * @version 1.0, 2019/3/31
 */
 
 
interface NewsApiService {

    @GET(EndPoint.PATH_NEWS)
    fun fetchNews() : Single<List<NewsModel>>

    private object EndPoint{
        const val PATH_NEWS = "/carousell-interview-assets/android/carousell_news.json"
    }
}