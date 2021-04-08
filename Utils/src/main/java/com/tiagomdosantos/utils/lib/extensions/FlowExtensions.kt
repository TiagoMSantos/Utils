package com.tiagomdosantos.utils.lib.extensions

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

fun <T> Flow<T>.debounce(waitMillis: Long = 1000L) = flow {
    coroutineScope {
        val context = coroutineContext
        var delayPost: Deferred<Unit>? = null
        collect {
            delayPost?.cancel()
            delayPost = async(Dispatchers.Default) {
                delay(waitMillis)
                withContext(context) {
                    emit(it)
                }
            }
        }
    }
}
