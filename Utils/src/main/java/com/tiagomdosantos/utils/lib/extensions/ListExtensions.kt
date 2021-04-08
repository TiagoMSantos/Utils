package com.tiagomdosantos.utils.lib.extensions

fun <T> List<T>.toArrayList(): ArrayList<T> {
    return ArrayList(this)
}
