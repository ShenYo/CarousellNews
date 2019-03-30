package com.carousell.news

import com.google.gson.annotations.SerializedName

data class NewsModel(
    @SerializedName("description")
    val description: String,
    @SerializedName("time_created")
    val timeCreated: Long,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("banner_url")
    val bannerUrl: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String
)