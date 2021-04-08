package com.tiagomdosantos.utils.lib.extensions

import android.content.SharedPreferences

inline fun SharedPreferences.edit(func: SharedPreferences.Editor.() -> Unit) {
    edit().apply {
        func()
        apply()
    }
}
