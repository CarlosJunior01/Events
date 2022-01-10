package com.carlos.events.domain.usecase

import com.carlos.events.data.repository.EventsRepository
import com.carlos.events.data.response.CheckInResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CheckInUseCaseImpl (
    private val eventsRepository: EventsRepository
): CheckInUseCase {

    override fun checkIn(checkInResponse: CheckInResponse): Flow<Any> = flow {
        val response = eventsRepository.postCheckIn(checkInResponse)
        emit(response)
    }
}