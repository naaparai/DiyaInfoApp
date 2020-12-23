/*
 * Copyright (c) Koninklijke Philips N.V., 2020.
 * All rights reserved.
 */

package com.example.diyainfoapp.global

import android.content.Context
import android.content.Intent
import android.os.Build
import com.example.diyainfoapp.BuildConfig
import com.example.diyainfoapp.R
import java.net.InetAddress
import java.net.UnknownHostException
import java.util.*

const val MB_TO_BYTE = 1000000

/*
This Api does a ping to google to validate active network,
hence preferred to call on background thread
 */
fun isNetworkActive(): Boolean {
    return try {
        val address: InetAddress = InetAddress.getByName("www.google.com")
        !address.equals("")
    } catch (e: UnknownHostException) {
        false
    }
}

fun share(mContext: Context, type: String, title: String, url: String) {
    val intent = Intent(Intent.ACTION_SEND)
    val shareBody = String.format(
        mContext.getString(R.string.shareBody),
        type, title, url
    )
    intent.type = "text/plain"
    intent.putExtra(
        Intent.EXTRA_SUBJECT,
        mContext.getString(R.string.share_subject)
    )
    intent.putExtra(Intent.EXTRA_TEXT, shareBody)
    mContext.startActivity(Intent.createChooser(intent, mContext.getString(R.string.app_name)))
}


fun getContactUsBody(context: Context): String {
    context.run {
        val freeBytesInternal: Long =
            java.io.File(filesDir.absoluteFile.toString()).freeSpace / MB_TO_BYTE
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        val autoText = getString(R.string.automatically_fill_information)
        val fromAndroid = getString(R.string.sent_from_android)
        val pair = getAppVersionDetails()
        val version = pair.first
        val versionCode = pair.second
        var capitalizeManufacturer = ""
        if (manufacturer != null) {
            capitalizeManufacturer =
                manufacturer.substring(0, 1)
                    .toUpperCase(Locale.getDefault()) + manufacturer.substring(
                    1,
                    manufacturer.length
                ).toLowerCase(Locale.getDefault()) + " "
        }
        return "\n\n\n\n--- \nOS: " + Build.VERSION.RELEASE +
                "\nDevice: " + capitalizeManufacturer + model +
                " \nApp: " + packageName + "\nVersion: " + version +
                "\n" + "Version Code: " +
                versionCode + "\n" +
                getString(R.string.free_space) + ": $freeBytesInternal MBs" + "\n\n " + autoText +
                "\n\n\n" + fromAndroid
    }
}

fun getAppVersionDetails(): Pair<String?, Int> {
    val version: String?
    version = BuildConfig.VERSION_NAME
    val versionCode: Int = BuildConfig.VERSION_CODE
    return Pair(version, versionCode)
}

