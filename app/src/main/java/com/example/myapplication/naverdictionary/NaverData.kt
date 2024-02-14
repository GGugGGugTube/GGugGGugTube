package com.example.myapplication.naverdictionary

import com.google.gson.annotations.SerializedName

data class NaverData(
    @SerializedName("last_build_date")
    val lastBuildDate: String,

    @SerializedName("total")
    val total: Int,

    @SerializedName("start")
    val start: Int,

    @SerializedName("display")
    val display: Int,

    @SerializedName("items")
    val items: ArrayList<Items>
)

data class Items(
    @SerializedName("title")
    val title: String,

    @SerializedName("link")
    val link: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("thumbnail")
    val thumbnail: String
)




