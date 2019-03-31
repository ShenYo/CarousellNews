package com.carousell.features.news

import com.carousell.challenge.dataSource.repo.NewsRepo
import com.carousell.challenge.dataSource.repo.NewsRepoImpl
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext

/**
 * Description:
 *
 * @author Shenyo
 * @version 1.0, 2019/3/31
 */


class NewsPageTest {

    companion object {
        @BeforeClass
        @JvmStatic
        fun beforeClass() {
            StandAloneContext.loadKoinModules(module {
                single<NewsRepo> { NewsRepoImpl() }
            })
        }

        @AfterClass
        @JvmStatic
        fun afterClass() {
            StandAloneContext.stopKoin()
        }
    }


    @Test
    fun `user select Recent from menu`(){

    }

    @Test
    fun `user select popular from menu`(){

    }
}