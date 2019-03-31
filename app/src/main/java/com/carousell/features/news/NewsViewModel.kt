package com.carousell.features.news

import androidx.lifecycle.ViewModel
import com.carousell.base.applySchedulers
import com.carousell.dataSource.model.NewsModel
import com.carousell.dataSource.repo.NewsRepoImpl
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

    private val newsRepo by inject<NewsRepoImpl>()

    fun getNews(): Single<List<NewsModel>> {
        return newsRepo.getNews()
            .doOnSubscribe { publishSubject.onNext(LoadingState.LOADING) }
            .doOnSuccess { publishSubject.onNext(LoadingState.LOADED) }
            .doOnError { publishSubject.onNext(LoadingState.FAIL) }
            .applySchedulers()
    }

    fun fetchNews() : Completable {
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
        ).let { period ->
            when {
                period.years > 0 -> {
                    "${period.years} years ago"
                }
                period.months > 0 -> {
                    "${period.months} months ago"
                }
                period.days / 7 < 4 -> {
                    "${period.days} days ago"
                }
                else -> {
                    "${period.days / 7} weeks ago"
                }
            }
        }

    }

    fun getNewsSortedByTime(): Single<List<NewsModel>> {
        return newsRepo.getNews()
            .map { it.sortedByDescending { data -> data.timeCreated } }
            .doOnSubscribe { publishSubject.onNext(LoadingState.LOADING) }
            .doOnSuccess { publishSubject.onNext(LoadingState.LOADED) }
            .doOnError { publishSubject.onNext(LoadingState.FAIL) }
            .applySchedulers()
    }

    fun getNewsSortedByRank(): Single<List<NewsModel>> {
        return newsRepo.getNews()
            .map { it.sortedByDescending { data -> data.rank } }
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
