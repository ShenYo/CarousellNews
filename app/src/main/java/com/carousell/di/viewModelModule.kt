package com.carousell.di

import com.carousell.news.NewsViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Description:
 *
 * @author Shenyo
 * @version 1.0, 2019/3/30
 */
 
 
val viewModelModule = module {
    viewModel { NewsViewModel() }
}