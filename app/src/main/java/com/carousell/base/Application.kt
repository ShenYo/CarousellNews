package com.carousell.base

import android.app.Application
import com.carousell.di.viewModelModule
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.android.startKoin

/**
 * Description:
 *
 * @author Shenyo
 * @version 1.0, 2019/3/30
 */
 
 
class AppContext : Application()  {

    companion object {
        lateinit var INSTANCE: AppContext
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        initStetho()
        initTimeUtil()
        initDependencyInjection()
    }

    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
    }

    private fun initTimeUtil() {
        AndroidThreeTen.init(this)
    }

    private fun initDependencyInjection() {
        startKoin(this, listOf(
            viewModelModule
        ))
    }
}

