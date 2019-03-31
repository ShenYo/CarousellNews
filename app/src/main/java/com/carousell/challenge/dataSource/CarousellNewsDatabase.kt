package com.carousell.challenge.dataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carousell.challenge.dataSource.local.NewsDao
import com.carousell.challenge.dataSource.model.NewsModel

@Database(
    entities = [NewsModel::class],
    version = 1,
    exportSchema = false
)
abstract class CarousellNewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}