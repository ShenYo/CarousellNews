package com.carousell.base

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Description:
 *
 * @author lancewang
 * @version 1.0, 2019/3/30
 */



fun <T> Observable<T>.applySchedulers(): Observable<T> {
    return this
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.errorResume(): Observable<T> {
    return this.onErrorResumeNext(Observable.empty())
}


fun <T> Flowable<T>.applySchedulers(): Flowable<T> {
    return this
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Flowable<T>.errorResume(): Flowable<T> {
    return this.onErrorResumeNext(Flowable.empty())
}

fun <T> Single<T>.applySchedulers(): Single<T> {
    return this
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun Completable.applySchedulers(): Completable {
    return this
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Maybe<T>.applySchedulers(): Maybe<T> {
    return this
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

//TODO: check requirement
//fun <T> Flowable<T>.concat(observable: Observable<Any>): Flowable<T> {
//    return
//}