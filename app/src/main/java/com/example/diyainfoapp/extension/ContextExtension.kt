/*
 * Copyright (c) 2020. Kunsang Wangyal
 */

package com.example.diyainfoapp.extension

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.LifecycleOwner
import com.example.diyainfoapp.R

fun View.getLifeCycleOwner(): LifecycleOwner? {
    return if (this.context is AppCompatActivity) {
        this.context as AppCompatActivity
    } else {
        null
    }
}

fun Context.hideSoftInputKeyboard(view: View) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.showSoftInputKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
}

fun Context.pxFromDp(dp: Float): Float {
    return dp * resources.displayMetrics.density
}

@Suppress("DEPRECATION")
fun Context.isNetworkAvailable(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting == true
}

fun Context.isSystemNotificationTurnOn(): Boolean {
    if (NotificationManagerCompat.from(this).areNotificationsEnabled())
        return isOreoAndAboveNotificationTurnOn()
    return false
}

private fun Context.isOreoAndAboveNotificationTurnOn(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val manager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = manager.getNotificationChannel(getNotificationChannelId())
        return channel?.importance != NotificationManager.IMPORTANCE_NONE
    } else {
        true
    }
}

fun Context.getNotificationChannelId(): String {
    return "$packageName-${getString(R.string.app_name)}"
}

fun Context.openNotificationSetting() {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    intent.data = Uri.parse("package:$packageName")
    startActivity(intent)
}

fun Context.navigateToSettings() {
    startActivity(
        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", packageName, null)
        }
    )
}

fun Context.shareIntent(url: String) {
    try {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
        val shareMessage = "PDF file $url"
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
        this.startActivity(Intent.createChooser(shareIntent, "choose one"))
    } catch (e: Exception) {
        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
    }
}
