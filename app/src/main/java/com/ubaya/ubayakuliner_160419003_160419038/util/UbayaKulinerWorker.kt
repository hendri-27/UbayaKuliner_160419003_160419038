package com.ubaya.ubayakuliner_160419003_160419038.util

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class UbayaKulinerWorker(val context: Context, val params: WorkerParameters): Worker(context, params) {
    override fun doWork(): Result {
        NotificationHelper(context)
            .createNotification(inputData.getString("title").toString(),
                                inputData.getString("message").toString())
        return  Result.success()
    }
}