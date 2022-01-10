package com.carlos.events

import com.carlos.events.utils.BaseRobot

fun check(func: MainActivityAssertionRobot.() -> Unit) =
    MainActivityAssertionRobot().apply { func() }

open class MainActivityAssertionRobot : BaseRobot() {

    fun titleIsCorrect() {
        checkViewHasText(R.id.txtMainActivity, "Main Activity")
        checkViewIsDisplayed(R.id.txtMainActivity)
    }
}