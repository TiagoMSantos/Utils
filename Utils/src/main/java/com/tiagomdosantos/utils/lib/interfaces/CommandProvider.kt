package com.tiagomdosantos.utils.lib.interfaces

import com.tiagomdosantos.utils.lib.events.GenericCommand
import com.tiagomdosantos.utils.lib.events.SingleLiveEvent

interface CommandProvider {
    fun getCommand(): SingleLiveEvent<GenericCommand>
}
