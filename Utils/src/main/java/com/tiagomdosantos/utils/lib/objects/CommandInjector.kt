package com.tiagomdosantos.utils.lib.objects

import com.tiagomdosantos.utils.lib.events.GenericCommand
import com.tiagomdosantos.utils.lib.events.SingleLiveEvent
import com.tiagomdosantos.utils.lib.interfaces.CommandProvider

object CommandInjector : CommandProvider {

    override fun getCommand(): SingleLiveEvent<GenericCommand> =
        SingleLiveEvent()
}
