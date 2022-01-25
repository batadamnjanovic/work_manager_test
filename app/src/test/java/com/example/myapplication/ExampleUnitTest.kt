package com.example.myapplication

import android.os.Looper
import android.util.Log
import androidx.lifecycle.asFlow
import androidx.work.Configuration
import androidx.work.WorkManager
import androidx.work.testing.WorkManagerTestInitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows
import java.util.concurrent.Executor

@RunWith(RobolectricTestRunner::class)
class ExampleUnitTest {

    @Test
    fun testWorker() = runBlocking {
        val context = RuntimeEnvironment.getApplication().applicationContext
        val config = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setExecutor {
                GlobalScope.launch(Dispatchers.Default) {
                    it.run()
                }
            }
            .setTaskExecutor(Executor {
                GlobalScope.launch(Dispatchers.Default) {
                    it.run()
                }
            })
            .build()

        WorkManagerTestInitHelper.initializeTestWorkManager(context, config)

        val workManager = WorkManager.getInstance(context)

        val request = UploadWorker.createWorkerRequest()
        workManager.enqueue(request)

//        var a = 0
//        while (a++ < 50) {
//            val workInfo = workManager.getWorkInfoById(request.id).await()
//            println("work info: $workInfo")
//            Shadows.shadowOf(Looper.getMainLooper()).idle()
//        }

        val job = GlobalScope.launch {
            workManager.getWorkInfoByIdLiveData(request.id).asFlow().collect { workInfo ->
                println("info: $workInfo")
            }
        }

//        workManager.getWorkInfoByIdLiveData(request.id).observeForever { workInfo ->
//            println("observe info: $workInfo")
//        }

        while (job.isActive) {
            Shadows.shadowOf(Looper.getMainLooper()).idle()
        }
    }
}