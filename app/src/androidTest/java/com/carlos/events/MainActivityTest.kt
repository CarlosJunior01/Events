package com.carlos.events

import org.junit.Test

class MainActivityTest {

    @Test
    fun checkTitleIsCorrect() {
        onActivity {
            setUp()
            launch()
        }
        check {
            titleIsCorrect()
        }
    }
}