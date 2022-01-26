package com.example.myapplication

import android.content.Context
import android.util.Log
import androidx.multidex.BuildConfig
import androidx.startup.Initializer
import androidx.work.Configuration
import androidx.work.WorkManager
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

class WorkerInitializer : Initializer<WorkManager> {

    @InstallIn(SingletonComponent::class)
    @EntryPoint
    internal interface WorkManagerInitializerEntryPoint {
    }

    override fun create(context: Context): WorkManager {
        // TODO Crash is happening here. Tried also with https://dagger.dev/hilt/early-entry-point
        // TODO but unsuccessful
        EntryPointAccessors.fromApplication<WorkManagerInitializerEntryPoint>(context)

        val configuration = Configuration.Builder()
            .setMinimumLoggingLevel(if (BuildConfig.DEBUG) Log.VERBOSE else Log.INFO)
            .build()
        WorkManager.initialize(context, configuration)
        return WorkManager.getInstance(context)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}