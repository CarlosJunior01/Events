package com.carlos.events.usecase

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Test

@ExperimentalCoroutinesApi
class GetEventsUseCaseImplTest {

    @After
    fun tearDown() {
        GetEventsUseCaseImplRobot.tearDown()
    }

    @Test
    fun `when error should emit success response`(){
        GetEventsUseCaseImplRobot.apply {
            arrange{
                mockSuccess()
            }

            act {
                getEvent()
            }

            assert {
                runBlockingTest {
                    isSuccess()
                }
            }
        }
    }

    @Test
    fun `when error should emit error response`(){
        GetEventsUseCaseImplRobot.apply {
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