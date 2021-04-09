package com.tiagomdosantos.utils.lib.databinding

import android.widget.ExpandableListView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object RecyclerViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("smoothScrollToPosition")
    fun RecyclerView.scrollToPosition(position: Int) {
        smoothScrollToPosition(position)
    }

    @JvmStatic
    @BindingAdapter("scrollToTop")
    fun ExpandableListView.scrollToTop(isRequired: Boolean) {
        takeIf { isRequired }?.run {
            smoothScrollToPosition(0)
        }
    }
}
