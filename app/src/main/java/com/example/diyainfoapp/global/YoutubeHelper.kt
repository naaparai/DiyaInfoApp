/*
 * Copyright (c) 2020. Kunsang Wangyal
 */

package com.app.tersarkhang.global

import java.net.MalformedURLException
import java.net.URL

@Throws(MalformedURLException::class)
fun extractYoutubeId(url: String?): String? {
    val query: String = URL(url).query
    val param = query.split("&".toRegex()).toTypedArray()
    var id: String? = null
    for (row in param) {
        val param1 = row.split("=".toRegex()).toTypedArray()
        if (param1[0] == "v") {
            id = param1[1]
        }
    }
    return id
}
