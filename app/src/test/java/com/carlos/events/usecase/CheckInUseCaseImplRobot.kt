package com.carlos.events.usecase

import com.carlos.events.data.repository.EventsRepository
import com.carlos.events.data.response.CheckInResponse
import com.carlos.events.domain.model.EventsDomain
import com.carlos.events.domain.usecase.CheckInUseCaseImpl
import com.google.common.truth.Truth.assertThat
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

object CheckInUseCaseImplRobot {

    private val eventsRepository = mockk<EventsRepository>()
    private lateinit var checkInUseCaseImpl: CheckInUseCaseImpl
    private lateinit var flowResponse: Flow<Any>
    private var checkInValue = CheckInResponse(eventId = "1", name = "Aline", email = "aline@gmail")
    private val eventsValue = EventsDomain(
        description = "",
        image = "",
        price = 0.0F,
        title = "",
        id = ""
    )

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
        fun mockError() {
            coEvery { eventsRepository.getEvents() } throws Exception()
        }
    }

    class EventsAct() {
        fun getEvent() {
            checkInUseCaseImpl = CheckInUseCaseImpl(eventsRepository)
            flowResponse = checkInUseCaseImpl.checkIn(checkInValue)
        }
    }

    class EventsAssert {
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
