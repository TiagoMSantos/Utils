package com.tiagomdosantos.utils.lib.configurations

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.fragment.app.Fragment
import com.tiagomdosantos.utils.lib.BR

class FragmentConfiguration() : BaseObservable() {

    @get:Bindable
    var fragment: Fragment? = null
        set(fragment) {
            field = fragment
            notifyPropertyChanged(BR.fragment)
        }
    @get:Bindable
    var backStack: String? = null
        set(backStack) {
            field = backStack
            notifyPropertyChanged(BR.backStack)
        }
    @get:Bindable
    var popEnterAnim: Int = 0
        set(popEnterAnim) {
            field = popEnterAnim
            notifyPropertyChanged(BR.popEnterAnim)
        }
    @get:Bindable
    var popExitAnim: Int = 0
        set(popExitAnim) {
            field = popExitAnim
            notifyPropertyChanged(BR.popExitAnim)
        }

    @get:Bindable
    var enterAnim: Int = 0
        set(enterAnim) {
            field = enterAnim
            notifyPropertyChanged(BR.enterAnim)
        }

    @get:Bindable
    var exitAnim: Int = 0
        set(exitAnim) {
            field = exitAnim
            notifyPropertyChanged(BR.exitAnim)
        }

    val isCustomAnimation: Boolean
        get() = this.enterAnim != 0 && this.exitAnim != 0

    val isCustomPopBackAnimation: Boolean
        get() = this.popEnterAnim != 0 && this.popExitAnim != 0

    fun hasBackStack(): Boolean {
        return !this.backStack.isNullOrEmpty()
    }
}
