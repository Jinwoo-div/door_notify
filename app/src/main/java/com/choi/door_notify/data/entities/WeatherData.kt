package com.choi.door_notify.data.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class WeatherData (
    @SerializedName("response")
    @Expose
    var response: Response? = null
)

data class Body (
    @SerializedName("dataType")
    @Expose
    var dataType: String? = null,

    @SerializedName("items")
    @Expose
    var items: Items? = null,

    @SerializedName("pageNo")
    @Expose
    var pageNo: Int? = null,

    @SerializedName("numOfRows")
    @Expose
    var numOfRows: Int? = null,

    @SerializedName("totalCount")
    @Expose
    var totalCount: Int? = null
)

data class Header (
    @SerializedName("resultCode")
    @Expose
    var resultCode: String? = null,

    @SerializedName("resultMsg")
    @Expose
    var resultMsg: String? = null
)

data class Item (
    @SerializedName("baseDate")
    @Expose
    var baseDate: String? = null,

    @SerializedName("baseTime")
    @Expose
    var baseTime: String? = null,

    @SerializedName("category")
    @Expose
    var category: String? = null,

    @SerializedName("fcstDate")
    @Expose
    var fcstDate: String? = null,

    @SerializedName("fcstTime")
    @Expose
    var fcstTime: String? = null,

    @SerializedName("fcstValue")
    @Expose
    var fcstValue: String? = null,

    @SerializedName("nx")
    @Expose
    var nx: Int? = null,

    @SerializedName("ny")
    @Expose
    var ny: Int? = null
)

data class Items (
    @SerializedName("item")
    @Expose
    var item: List<Item>? = null
)

data class Response (
    @SerializedName("header")
    @Expose
    var header: Header? = null,

    @SerializedName("body")
    @Expose
    var body: Body? = null
)
