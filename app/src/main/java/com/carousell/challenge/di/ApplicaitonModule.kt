package com.carousell.challenge.di

import androidx.room.Room
import com.carousell.challenge.base.AppContext
import com.carousell.challenge.dataSource.CarousellNewsDatabase
import com.carousell.challenge.dataSource.remote.NewsApiService
import com.carousell.challenge.dataSource.repo.NewsRepo
import com.carousell.challenge.dataSource.repo.NewsRepoImpl
import com.carousell.challenge.news.NewsViewModel
import com.carousell.challenge.util.ResourceManager
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
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
    single<OkHttpClient> {
        OkHttpClient().newBuilder()
            .addNetworkInterceptor(StethoInterceptor())
            .build()
    }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(AppContext.API_ROOT)
            .client(get())
            .addCallAdapterFactory(get<RxJava2CallAdapterFactory>())
            .addConverterFactory(get<GsonConverterFactory>())
            .build()
    }
}

val repositoryModule = module {
    single<NewsRepo> { NewsRepoImpl() }
}

val remoteDataSourceModule = module {
    single<NewsApiService> { get<Retrofit>().create(NewsApiService::class.java) }
}

val localDataSourceModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            CarousellNewsDatabase::class.java,
            CarousellNewsDatabase::class.java.simpleName
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<CarousellNewsDatabase>().newsDao() }
}

val viewModelModule = module {
    viewModel { NewsViewModel() }
}

val resourceModule = module {
    single { ResourceManager() }
}