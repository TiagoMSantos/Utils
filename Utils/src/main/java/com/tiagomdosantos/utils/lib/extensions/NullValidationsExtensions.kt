package com.tiagomdosantos.utils.lib.extensions

import androidx.databinding.ObservableField

fun String?.isNotNullOrEmpty(): Boolean {
    return this != null && this.trim { it <= ' ' }.isNotEmpty()
}

fun CharSequence?.isNotNullOrEmpty(): Boolean {
    return this != null && this.toString().trim { it <= ' ' }.isNotEmpty()
}

fun ObservableField<String>?.isNotNullOrEmpty(): Boolean {
    return this != null && this.get().isNotNullOrEmpty()
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

fun String?.isNullOrEmpty(): Boolean {
    return this.isNotNullOrEmpty().not()
}

fun CharSequence?.isNullOrEmpty(): Boolean {
    return this.isNotNullOrEmpty().not()
}

fun ObservableField<String>?.isNullOrEmpty(): Boolean {
    return this.isNotNullOrEmpty().not()
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
