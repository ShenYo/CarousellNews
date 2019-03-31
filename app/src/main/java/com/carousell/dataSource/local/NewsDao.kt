package com.carousell.dataSource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.carousell.dataSource.model.NewsModel
import io.reactivex.Single

/**
 * Description:
 *
 * @author Shenyo
 * @version 1.0, 2019/3/31
 */


@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: List<NewsModel>): List<Long>

    @Query("SELECT * from news")
    fun getNews(): Single<List<NewsModel>>

}