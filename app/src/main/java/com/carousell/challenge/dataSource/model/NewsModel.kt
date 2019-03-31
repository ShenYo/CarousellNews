package com.carousell.challenge.dataSource.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "news")
data class NewsModel(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @ColumnInfo
    @SerializedName("description")
    val description: String,
    @ColumnInfo
    @SerializedName("time_created")
    val timeCreated: Long,
    @ColumnInfo
    @SerializedName("rank")
    val rank: Int,
    @ColumnInfo
    @SerializedName("banner_url")
    val bannerUrl: String,
    @ColumnInfo
    @SerializedName("title")
    val title: String
)