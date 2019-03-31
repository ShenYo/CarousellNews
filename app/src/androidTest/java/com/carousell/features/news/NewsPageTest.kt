package com.carousell.features.news

import com.carousell.challenge.dataSource.repo.NewsRepo
import com.carousell.challenge.news.NewsViewModel
import com.carousell.dataSource.repo.MockNewsRepo
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.koin.dsl.module.module
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext

/**
 * Description:
 *
 * @author Shenyo
 * @version 1.0, 2019/3/31
 */


class NewsPageTest : KoinComponent {

    private val newsViewModel = NewsViewModel()

    companion object {
        @BeforeClass
        @JvmStatic
        fun beforeClass() {
            StandAloneContext.loadKoinModules(module {
                RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
                RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
                RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
                single<NewsRepo>(override = true) { MockNewsRepo() }
            })
        }

        @AfterClass
        @JvmStatic
        fun afterClass() {
            StandAloneContext.stopKoin()
        }
    }

    @Test
    fun userSelectRecentSorting() {
        val sortedNewsIdList = listOf(
            "122", "121", "123", "125", "126", "124"
        )

        newsViewModel.getNewsOrderByTime()
            .test()
            .assertValue { news ->
                news.forEachIndexed { index, newsModel ->
                    if (newsModel.id != sortedNewsIdList[index]) {
                        return@assertValue false
                    }
                }
                true
            }
            .assertNoErrors()
            .assertComplete()

    }

    @Test
    fun userSelectPopularSorting() {
        val sortedNewsIdList = listOf(
            "126", "122", "125", "121", "123", "124"
        )

        newsViewModel.getNewsOrderByRank()
            .test()
            .assertValue { news ->
                news.forEachIndexed { index, newsModel ->
                    if (newsModel.id != sortedNewsIdList[index]) {
                        return@assertValue false
                    }
                }
                true
            }
            .assertNoErrors()
            .assertComplete()
    }

}