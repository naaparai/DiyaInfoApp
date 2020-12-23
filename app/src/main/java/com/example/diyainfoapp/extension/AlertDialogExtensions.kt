/*
 * Copyright (c) Koninklijke Philips N.V., 2020.
 * All rights reserved.
 */

package com.app.tersarkhang.extension

import androidx.appcompat.app.AlertDialog.Builder

fun Builder.setYesButton(btnTitle: Int, callback: (() -> Unit)? = null): Builder {
    setPositiveButton(btnTitle) { dialog, _ ->
        dialog.dismiss()
        callback?.invoke()
    }
    return this
}

fun Builder.setNoButton(title: Int, callback: (() -> Unit)? = null): Builder {
    setNegativeButton(title) { dialog, _ ->
        dialog.dismiss()
        callback?.invoke()
    }
    return this
}
