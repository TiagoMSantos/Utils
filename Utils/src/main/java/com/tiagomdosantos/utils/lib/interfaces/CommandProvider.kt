package com.tiagomdosantos.utils.lib.interfaces

import com.tiagomdosantos.utils.lib.GenericCommand
import com.tiagomdosantos.utils.lib.SingleLiveEvent

interface CommandProvider {
    fun getCommand(): SingleLiveEvent<GenericCommand>
}
