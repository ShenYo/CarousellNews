package com.carousell.di

import com.carousell.base.AppContext
import com.carousell.base.ResourceManager
import com.carousell.dataSource.remote.NewsApiService
import com.carousell.dataSource.repo.NewsRepo
import com.carousell.news.NewsViewModel
import com.google.gson.Gson
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Description:
 *
 * @author Shenyo
 * @version 1.0, 2019/3/30
 */


val netWorkModule = module {
    single { Gson() }
    single<GsonConverterFactory> { GsonConverterFactory.create(get()) }
    single<RxJava2CallAdapterFactory> { RxJava2CallAdapterFactory.create() }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(AppContext.API_ROOT)
            .addCallAdapterFactory(get<RxJava2CallAdapterFactory>())
            .addConverterFactory(get<GsonConverterFactory>())
            .build()
    }
}

val repositoryModule = module {
    single { NewsRepo() }
}

val remoteDataSourceModule = module {
    single<NewsApiService>{ get<Retrofit>().create(NewsApiService::class.java) }
}

val localDataSourceModule = module {

}

val viewModelModule = module {
    viewModel { NewsViewModel() }
}

val resourceModule = module {
    single { ResourceManager() }
}