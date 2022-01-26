package com.example.myapplication

import android.content.Context
import androidx.work.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

internal class UploadWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    companion object {

        internal fun createWorkerRequest(): OneTimeWorkRequest =
            OneTimeWorkRequestBuilder<UploadWorker>()
                .build()
    }

    override suspend fun doWork(): Result {
        delay(1000) // TODO With delay it does not work

//        var a = 0
//        while (a++ < 100000) {
//            yield()
//        }
        return Result.success(workDataOf("test" to 2))
    }
}
