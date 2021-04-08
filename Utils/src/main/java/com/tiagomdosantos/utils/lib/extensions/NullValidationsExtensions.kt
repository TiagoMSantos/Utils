package com.tiagomdosantos.utils.lib.extensions

import androidx.databinding.ObservableField

fun isNotNullOrEmpty(s: String?): Boolean {
    return s != null && s.trim { it <= ' ' }.isNotEmpty()
}

fun isNotNullOrEmpty(s: CharSequence?): Boolean {
    return s != null && s.toString().trim { it <= ' ' }.isNotEmpty()
}

fun isNotNullOrEmpty(s: ObservableField<String>?): Boolean {
    return s != null && isNotNullOrEmpty(s.get())
}

fun isNotNullOrEmpty(vararg values: String): Boolean {
    for (s in values) {
        if (s == null || s.isEmpty())
            return false
    }

    return true
}

@SafeVarargs
fun isNotNullOrEmpty(vararg values: ObservableField<String>): Boolean {
    for (s in values) {
        if (s != null && s.get()!!.trim { it <= ' ' }.isNotEmpty())
            return false
    }

    return true
}

fun isNullOrEmpty(s: String?): Boolean {
    return !isNotNullOrEmpty(s)
}

fun isNullOrEmpty(s: CharSequence?): Boolean {
    return !isNotNullOrEmpty(s)
}

fun isNullOrEmpty(s: ObservableField<String>?): Boolean {
    return !isNotNullOrEmpty(s)
}

fun isNullOrEmpty(vararg values: String): Boolean {
    if (values != null) {
        for (s in values) {
            if (s == null || s.isEmpty())
                return true
        }
    }

    return false
}

@SafeVarargs
fun isNullOrEmpty(vararg values: ObservableField<String>): Boolean {
    if (values != null) {
        for (s in values) {
            if (s == null || s.get() == null || s.get() != null && s.get()!!.trim { it <= ' ' }.isEmpty())
                return true
        }
    }

    return false
}
