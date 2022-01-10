package com.choi.door_notify.data.local

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context) {
    val prefName = "loc"
    val pref: SharedPreferences = context.getSharedPreferences(prefName, 0)

    fun getString(key: String): String {
        return pref.getString(key, "").toString()
    }

    fun setString(key: String, str: String) {
        pref.edit().putString(key, str).apply()
    }
}