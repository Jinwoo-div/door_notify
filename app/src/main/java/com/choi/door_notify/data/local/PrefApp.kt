package com.choi.door_notify.data.local

import android.app.Application

class PrefApp: Application() {
    companion object {
        lateinit var pref: PreferenceUtil
    }

    override fun onCreate() {
        pref = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}