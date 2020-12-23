package com.example.diyainfoapp

import android.app.Application
import androidx.core.app.NotificationManagerCompat
import com.example.diyainfoapp.global.createNotificationChannel
import com.example.diyainfoapp.module.viewModelModule
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

open class DiyaInfoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        prepareDI()
        setUpNotificationChannel()
    }

    private fun prepareDI() {
        startKoin {
            androidContext(this@DiyaInfoApplication)
            modules(
                listOf(
                    viewModelModule
                )
            )
        }
    }

    private fun setUpNotificationChannel() {
        createNotificationChannel(
            this,
            NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            getString(R.string.app_name), "Sleep+ notification channel."
        )
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}
