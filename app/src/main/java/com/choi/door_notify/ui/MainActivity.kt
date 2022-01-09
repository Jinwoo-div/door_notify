package com.choi.door_notify.ui

import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.AssetManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import com.choi.door_notify.R
import com.choi.door_notify.data.entities.ForecastRequest
import com.choi.door_notify.data.entities.Location
import com.choi.door_notify.data.entities.Status
import com.choi.door_notify.data.local.LocationDatabase
import com.choi.door_notify.data.remote.WeatherService
import com.choi.door_notify.databinding.ActivityMainBinding
import com.choi.door_notify.utils.WholeStatusRVAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream

class MainActivity : AppCompatActivity(), WeatherView {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFullScreen()
        initListener()
        initDb(this)

        var sets = ArrayList<Status>()

        sets.add(Status(
            10,
            R.drawable.blizzard,
            30,
            10))

        binding.mainWholeStatusRv.adapter = WholeStatusRVAdapter(sets)

    }


    private fun initListener() {
        binding.mainCurrentStatusIv.setOnClickListener() {
            val numOfRows = 10
            val pageNo = 1
            val dataType = "JSON"
            val baseDate = "20220104"
            val baseTime = "0500"
            val nx = 50
            val ny = 120
            val req = ForecastRequest(numOfRows, pageNo, dataType, baseDate, baseTime, nx, ny)
            WeatherService().OnedayWeather(req)

        }
    }

    private fun setFullScreen() {
        supportActionBar?.hide()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)

            val ctrl = window.insetsController

            if (ctrl != null) {
                ctrl.hide(
                    WindowInsets.Type.statusBars() or
                            WindowInsets.Type.navigationBars()
                )

                ctrl.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_IMMERSIVE
                            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }

    fun initDb(context: Context) {
        val db = LocationDatabase.getInstance(context)!!
        if (db.locationDao().getFirst() == null) {
            val manager: AssetManager = context.assets
            val input: InputStream = manager.open("location(20210401).txt")

            input.bufferedReader().readLines().forEach {
                var token = it.split("\t")
                Log.e("tokaa", token.toString())
                CoroutineScope(Dispatchers.IO).launch {
                    var inp = Location(
                        token[0],
                        token[1],
                        token[2],
                        token[3],
                        token[4],
                        token[5].toInt(),
                        token[6].toInt()
                    )
                    db.locationDao().insert(inp)
                }
            }
        }
    }

    override fun onDataGetSuccess() {
        TODO("Not yet implemented")
    }
}