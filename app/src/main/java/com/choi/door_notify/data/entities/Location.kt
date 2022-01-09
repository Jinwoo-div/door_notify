package com.choi.door_notify.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LocationTable")
data class Location (
    var country: String = "",
    @PrimaryKey var code: String = "",
    var first: String = "",
    var second: String = "",
    var third: String = "",
    var x: Int = 0,
    var y: Int = 0
    )