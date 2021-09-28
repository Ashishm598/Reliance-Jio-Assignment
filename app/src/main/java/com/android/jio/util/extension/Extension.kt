package com.android.jio.util.extension

import android.util.Log
import android.view.View

private const val app_log = "jio_log"

fun String.log(initial: String? = null, tag: String? = app_log) {
    val msgInitial = if (initial != null) "$initial: " else ""
    Log.d(tag, "$msgInitial$this")
}

fun Any.log(initial: String? = null, tag: String? = app_log) {
    val msgInitial = if (initial != null) "$initial: " else ""
    Log.d(tag, "$msgInitial$this")
}

fun View.setVisibility(status: Boolean) {
    if (status) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}


interface Accessor<out T> {
    fun get(): T
}

fun <T> T.toAccessor() = object : Accessor<T> {
    override fun get(): T {
        return this@toAccessor
    }
}