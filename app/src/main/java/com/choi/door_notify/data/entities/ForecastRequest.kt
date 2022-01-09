package com.choi.door_notify.data.entities

data class ForecastRequest(
    val numOfRows: Int,
    val pageNo: Int,
    val dataType: String,
    val baseDate: String,
    val baseTime: String,
    val nx: Int,
    val ny: Int
)
