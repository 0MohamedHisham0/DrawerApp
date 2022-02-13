package com.m.hisham.painter.Utiltis

import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation

fun slideInLeft(view: View, duration : Long = 2000, repeatCount : Int = 0) : Animation {
    view.visibility = View.VISIBLE
    val inFromLeft: Animation = TranslateAnimation(
        Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
        Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f
    )
    inFromLeft.duration = duration
    inFromLeft.repeatCount = repeatCount
    view.startAnimation(inFromLeft)
    return  view.animation
}

fun slideInRight(view: View, duration : Long = 2000, repeatCount : Int = 0) : Animation {
    view.visibility = View.VISIBLE
    val inFromLeft: Animation = TranslateAnimation(
        Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
        Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f
    )
    inFromLeft.duration = duration
    inFromLeft.repeatCount = repeatCount
    view.startAnimation(inFromLeft)
    return  view.animation
}

fun slideInTop(view: View, duration : Long = 2000, repeatCount : Int = 0) : Animation {
    view.visibility = View.VISIBLE
    val inFromLeft: Animation = TranslateAnimation(
        Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
        Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_PARENT, 0.0f
    )
    inFromLeft.duration = duration
    inFromLeft.repeatCount = repeatCount
    view.startAnimation(inFromLeft)
    return  view.animation
}

fun slideInBottom(view: View, duration : Long = 2000, repeatCount : Int = 0) : Animation {
    view.visibility = View.VISIBLE
    val inFromLeft: Animation = TranslateAnimation(
        Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
        Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_PARENT, 0.0f
    )
    inFromLeft.duration = duration
    inFromLeft.repeatCount = repeatCount
    view.startAnimation(inFromLeft)
    return  view.animation
}

fun slideOutLeft(view: View, duration : Long = 2000, repeatCount : Int = 0) : Animation {
    view.visibility = View.VISIBLE
    val inFromLeft: Animation = TranslateAnimation(
        Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, -1.0f,
        Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f
    )
    inFromLeft.duration = duration
    inFromLeft.repeatCount = repeatCount
    view.startAnimation(inFromLeft)
    view.visibility = View.GONE
    return  view.animation
}

fun slideOutRight(view: View, duration : Long = 2000, repeatCount : Int = 0) : Animation {
    view.visibility = View.VISIBLE
    val inFromLeft: Animation = TranslateAnimation(
        Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 1.0f,
        Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f
    )
    inFromLeft.duration = duration
    inFromLeft.repeatCount = repeatCount
    view.startAnimation(inFromLeft)
    view.visibility = View.GONE
    return  view.animation
}

fun slideOutTop(view: View, duration : Long = 2000, repeatCount : Int = 0) : Animation {
    view.visibility = View.VISIBLE
    val inFromLeft: Animation = TranslateAnimation(
        Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
        Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, -1.0f
    )
    inFromLeft.duration = duration
    inFromLeft.repeatCount = repeatCount
    view.startAnimation(inFromLeft)
    view.visibility = View.GONE
    return  view.animation
}

fun slideOutBottom(view: View, duration : Long = 2000, repeatCount : Int = 0) : Animation {
    view.visibility = View.VISIBLE
    val inFromLeft: Animation = TranslateAnimation(
        Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
        Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 1.0f
    )
    inFromLeft.duration = duration
    inFromLeft.repeatCount = repeatCount
    view.startAnimation(inFromLeft)
    view.visibility = View.GONE
    return  view.animation
}
