package com.my.mypaging3.work

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.work.*
import com.my.mypaging3.R
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

class WorkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work)

        val uploadWorkRequest: WorkRequest =
        //OneTimeWorkRequestBuilder<UploadWorker>()
            //    .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            PeriodicWorkRequestBuilder<UploadWorker>(15L, TimeUnit.MINUTES)
                .setInputData(workDataOf("some_options" to "VALUE"))
                .build()

        val workManager = WorkManager.getInstance(applicationContext)

        workManager.getWorkInfoByIdLiveData(uploadWorkRequest.id).observeForever {
            Log.d("EE", "${it.outputData}")
            Log.d("EE", "${it.progress}")
            Log.d("EE", "${it.state}")

            if (it.state == WorkInfo.State.FAILED || it.state == WorkInfo.State.CANCELLED) {
                //        notificationConfig.cancelNotification()
            }
        }

        //   notificationConfig.makeNotification("message")
        workManager.enqueue(uploadWorkRequest)

        //NotificationConfig(applicationContext).showNotification("sd", UUID.randomUUID())
    }
}

class UploadWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    //Worker or WorkManager manages notification's lifecycle
    private val notificationConfig by lazy { NotificationConfig(applicationContext) }

    //override suspend fun getForegroundInfo() = ForegroundInfo(
    //    notificationConfig.getNotificationId(),
    //    notificationConfig.createNotification(id)
    //)

    override suspend fun doWork(): Result {

        setForeground(
            ForegroundInfo(
                notificationConfig.getNotificationId(),
                notificationConfig.createNotification(id)
            )
        )

        val value = inputData.getString("some_options")
        Log.d("EE", "WORKER = $value")

        setProgress(workDataOf("Progress" to 0))
        delay(10_000)
        setProgress(workDataOf("Progress" to 10))
        delay(10_000)
        setProgress(workDataOf("Progress" to 20))
        delay(10_000)
        setProgress(workDataOf("Progress" to 30))
        delay(10_000)

        //return Result.retry()

        setProgress(workDataOf("Progress" to 40))
        delay(10_000)
        setProgress(workDataOf("Progress" to 50))
        delay(10_000)
        setProgress(workDataOf("Progress" to 60))
        delay(10_000)
        setProgress(workDataOf("Progress" to 100))

        // Indicate whether the work finished successfully with the Result
        return Result.success()
    }
}
