package com.carlos.events.usecase

import com.carlos.events.data.repository.EventsRepository
import com.carlos.events.domain.model.EventsDomain
import com.carlos.events.domain.usecase.GetEventsUseCaseImpl
import com.google.common.truth.Truth.assertThat
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.singleOrNull

object GetEventsUseCaseImplRobot {

    private val eventsRepository = mockk<EventsRepository>()
    private lateinit var getEventsUseCase: GetEventsUseCaseImpl
    private lateinit var flowResponse: Flow<Any>
    private val eventsValue = EventsDomain(
        description = "",
        image = "",
        price = 0.0F,
        title = "",
        id = ""
    )

    private val expectedList = listOf(eventsValue)

    fun tearDown() {
        unmockkAll()
        clearAllMocks()
    }

    infix fun arrange(func: EventsArrange.() -> Unit) =
        EventsArrange().apply(func)

    infix fun act(func: EventsAct.() -> Unit) =
        EventsAct().apply(func)

    infix fun assert(func: EventsAssert.() -> Unit) =
        EventsAssert().apply(func)

    class EventsArrange {

        fun mockSuccess() {
            coEvery{ eventsRepository.getEvents()
            } returns expectedList
        }

        fun mockError() {
            coEvery { eventsRepository.getEvents() } throws Exception()
        }
    }

    class EventsAct() {
        fun getEvent() {
            getEventsUseCase = GetEventsUseCaseImpl(eventsRepository)
            flowResponse = getEventsUseCase.getEvents()
        }
    }

    class EventsAssert {

        suspend fun isSuccess() {
            val list = flowResponse.singleOrNull()
            assertThat(list).isNotNull()
        }

        suspend fun isError() {
            var isError = false
            flowResponse.catch {
                isError = true
            }.collect {
                isError = false
            }
            assertThat(isError).isTrue()
        }
    }
}