package com.tiagomdosantos.utils.lib.extensions

import androidx.lifecycle.MutableLiveData
import com.tiagomdosantos.utils.lib.enums.ViewState

fun MutableLiveData<ViewState>.loading() {
    this.value = ViewState.LOADING
}

fun MutableLiveData<ViewState>.loaded() {
    this.value = ViewState.LOADED
}

fun MutableLiveData<ViewState>.failed() {
    this.value = ViewState.FAILED
}

fun MutableLiveData<ViewState>.actionFailed() {
    this.value = ViewState.ACTION_FAILED
}
