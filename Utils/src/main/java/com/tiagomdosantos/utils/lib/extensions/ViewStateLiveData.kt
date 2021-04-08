package com.tiagomdosantos.utils.lib.extensions

import androidx.lifecycle.MutableLiveData
import com.tiagomdosantos.utils.lib.enums.ViewState

fun MutableLiveData<ViewState>.loading() {
    this.value = ViewState.LOADING
}

fun MutableLiveData<ViewState>.loaded() {
    this.value = ViewState.LOADED
}

fun MutableLiveData<ViewState>.error() {
    this.value = ViewState.ERROR
}

fun MutableLiveData<ViewState>.actionError() {
    this.value = ViewState.ACTION_ERROR
}
