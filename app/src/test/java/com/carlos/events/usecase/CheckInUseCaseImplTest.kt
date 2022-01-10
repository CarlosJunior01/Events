package com.carlos.events.usecase

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Test

@ExperimentalCoroutinesApi
class CheckInUseCaseImplTest {

    @After
    fun tearDown() {
        CheckInUseCaseImplRobot.tearDown()
    }

    @Test
    fun `when error should emit error response`(){
        CheckInUseCaseImplRobot.apply {
            arrange{
                mockError()
            }
            act {
                getEvent()
            }

            assert {
                runBlockingTest {
                    isError()
                }
            }
        }
    }
}