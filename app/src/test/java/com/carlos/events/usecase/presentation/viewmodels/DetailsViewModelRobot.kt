package com.carlos.events.usecase.presentation.viewmodels

import com.carlos.events.data.response.CheckInResponse
import com.carlos.events.domain.model.EventsDomain
import com.carlos.events.domain.usecase.CheckInUseCase
import com.carlos.events.domain.usecase.GetEventsUseCase
import com.carlos.events.presentation.viewmodels.EventsViewModel
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.every
import io.mockk.verify
import io.mockk.clearAllMocks
import kotlinx.coroutines.flow.flow

object DetailsViewModelRobot {

    private lateinit var viewModel: EventsViewModel
    private val getEventsUseCase = mockk<GetEventsUseCase>(relaxed = true)
    private val checkInUseCase = mockk<CheckInUseCase>(relaxed = true)

    private val eventsValue = EventsDomain(
        description = "",
        image = "",
        price = 0.0F,
        title = "",
        id = ""
    )

    private val checkInValue = CheckInResponse(eventId = "1", name = "Aline", email = "aline@gmail")

    fun tearDown() {
        unmockkAll()
        clearAllMocks()
    }

    infix fun arrange(func: EventsFragmentArrange.() -> Unit) =
        EventsFragmentArrange().apply(func)

    infix fun act(func: EventsFragmentAct.() -> Unit) =
        EventsFragmentAct().apply(func)

    infix fun assert(func: EventsFragmentAssert.() -> Unit) =
        EventsFragmentAssert().apply(func)

    class EventsFragmentArrange() {

        fun mockSuccess() {
            every { getEventsUseCase.getEvents() } returns flow { emit(listOf(eventsValue)) }
        }

        fun mockCheckIn() {
            every { checkInUseCase.checkIn(checkInValue)  } returns flow { emit(listOf(eventsValue)) }
        }
    }

    class EventsFragmentAct() {
        fun initViewModel() {
            viewModel = EventsViewModel(getEventsUseCase, checkInUseCase)
        }

    }

    class EventsFragmentAssert() {

        fun isGetEvents() {
            verify(exactly = 0 ) {
                getEventsUseCase.getEvents()
            }
        }

        fun isChekIn() {
            verify(exactly = 0 ) {
                checkInUseCase.checkIn(checkInValue)
            }
        }
    }
}