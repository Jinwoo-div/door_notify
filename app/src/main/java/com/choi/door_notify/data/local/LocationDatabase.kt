package com.choi.door_notify.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.choi.door_notify.data.entities.Location

@Database(entities=[Location::class], version = 1)
abstract class LocationDatabase: RoomDatabase() {
    abstract fun locationDao(): LocationDao

    companion object {
        private var instance: LocationDatabase? = null

        @Synchronized
        fun getInstance(context: Context): LocationDatabase? {
            if (instance == null) {
                synchronized(LocationDatabase::class) {//synchronized block-> 클래스자체를 동기화->클래스 사용해 생성하는 모든 쓰레드 동기화
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LocationDatabase::class.java,
                        "location_database"
                    ).allowMainThreadQueries().build()
                }
            }
            return instance
        }
    }
}