package com.example.myapplication

import android.app.Application
import android.util.Log
import androidx.work.*

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val configuration = Configuration.Builder()
            .setMinimumLoggingLevel(if (BuildConfig.DEBUG) Log.VERBOSE else Log.INFO)
            .build()
        WorkManager.initialize(applicationContext, configuration)
    }
}