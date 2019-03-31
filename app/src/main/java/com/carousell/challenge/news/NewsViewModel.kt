package com.carousell.challenge.news

import androidx.lifecycle.ViewModel
import com.carousell.challenge.base.applySchedulers
import com.carousell.challenge.dataSource.model.NewsModel
import com.carousell.challenge.dataSource.repo.NewsRepo
import com.carousell.challenge.util.TimeFormatter
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.Period
import org.threeten.bp.ZoneId

class NewsViewModel : ViewModel(), KoinComponent {

    val publishSubject = PublishSubject.create<LoadingState>()

    private val newsRepo by inject<NewsRepo>()

    fun fetchNews(): Completable {
        return newsRepo.fetchNews()
            .doOnSubscribe { publishSubject.onNext(LoadingState.LOADING) }
            .doOnComplete { publishSubject.onNext(LoadingState.LOADED) }
            .doOnError { publishSubject.onNext(LoadingState.FAIL) }
            .applySchedulers()
    }

    fun getTextCreatedAt(timeCreated: Long): String {
        return Period.between(
            Instant.ofEpochSecond(timeCreated).atZone(ZoneId.systemDefault()).toLocalDate(),
            LocalDate.now()
        ).let { period -> TimeFormatter.format(period) }

    }

    fun getNewsOrderByTime(): Single<List<NewsModel>> {
        return newsRepo.getNews()
            .map { it.sortedByDescending { data -> data.timeCreated } }
            .doOnSubscribe { publishSubject.onNext(LoadingState.LOADING) }
            .doOnSuccess { publishSubject.onNext(LoadingState.LOADED) }
            .doOnError { publishSubject.onNext(LoadingState.FAIL) }
            .applySchedulers()
    }

    fun getNewsOrderByRank(): Single<List<NewsModel>> {
        return newsRepo.getNews()
            .map {
                it.sortedWith(compareByDescending<NewsModel> { news -> news.rank }
                    .thenByDescending { news -> news.timeCreated })
            }
            .doOnSubscribe { publishSubject.onNext(LoadingState.LOADING) }
            .doOnSuccess { publishSubject.onNext(LoadingState.LOADED) }
            .doOnError { publishSubject.onNext(LoadingState.FAIL) }
            .applySchedulers()
    }

}

enum class LoadingState {
    LOADING,
    LOADED,
    FAIL
}
