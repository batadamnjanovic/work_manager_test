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
        var a = 0
        while (a++ < 50) {
            yield()
        }
        return Result.success(workDataOf("test" to 2))
    }
}
