package com.tiagomdosantos.utils.lib.configurations

import android.animation.Animator
import androidx.annotation.IntDef
import androidx.annotation.RawRes
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.ObservableInt
import com.airbnb.lottie.LottieAnimationView
import com.tiagomdosantos.utils.lib.BR

class LottieConfiguration : BaseObservable() {
    @RawRes
    @get:Bindable
    var animation: Int = 0
        set(animation) {
            field = animation
            notifyPropertyChanged(BR.animation)
        }
    @get:Bindable
    var isLoop: Boolean = false
        set(loop) {
            field = loop
            notifyPropertyChanged(BR.loop)
        }
    @get:Bindable
    var scale = -1f
        set(scale) {
            field = scale
            notifyPropertyChanged(BR.scale)
        }
    @get:Bindable
    var speed = -1f
        set(speed) {
            field = speed
            notifyPropertyChanged(BR.speed)
        }
    @get:Bindable
    var progress = 0f
        set(progress) {
            field = progress
            notifyPropertyChanged(BR.progress)
        }
    @get:Bindable
    var startDelay: Long = 0
        set(startDelay) {
            field = startDelay
            notifyPropertyChanged(BR.startDelay)
        }
    @get:Bindable
    var animatorListener: Animator.AnimatorListener? = null
        set(animatorListener) {
            field = animatorListener
            notifyPropertyChanged(BR.animatorListener)
        }
    private val animationState = ObservableInt(PLAY)

    fun animationState(animationState: Int) {
        this.animationState.set(animationState)
    }
    //endregion

    fun builder(lottieView: LottieAnimationView) {
        animationState.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: androidx.databinding.Observable, propertyId: Int) {
                when (animationState.get()) {
                    PLAY -> lottieView.playAnimation()
                    RESUME ->
                        if (lottieView.isAnimating) {
                            lottieView.resumeAnimation()
                        } else {
                            lottieView.playAnimation()
                        }
                    PAUSE -> lottieView.pauseAnimation()
                    STOP -> {
                        lottieView.cancelAnimation()
                        lottieView.progress = 0f
                    }
                    NONE -> {
                    }
                    else -> {
                    }
                }
            }
        })

        if (animationState.get() == PLAY)
            lottieView.playAnimation()
    }

    //region --- ANIMATION STATE INTERFACE ---
    @Retention(AnnotationRetention.SOURCE)
    @IntDef(NONE, PLAY, RESUME, PAUSE, STOP)
    annotation class LottieAnimationState

    companion object {
        const val NONE = -1
        const val PLAY = 0
        const val RESUME = 1
        const val PAUSE = 2
        const val STOP = 3
    }
    //endregion
}
