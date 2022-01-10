package com.choi.door_notify.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.choi.door_notify.data.entities.Location

@Dao
interface LocationDao {
    @Insert
    suspend fun insert(location: Location)

    @Query("SELECT * FROM LocationTable WHERE code = '1100000000'")
    fun getFirst(): Location

    @Query("SELECT * FROM LocationTable")
    fun getAll(): List<Location>
}