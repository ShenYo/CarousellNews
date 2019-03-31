package com.carousell.dataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carousell.dataSource.local.NewsDao
import com.carousell.dataSource.model.NewsModel

@Database(
    entities = [NewsModel::class],
    version = 1
)
abstract class CarousellDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}