package com.carlos.events.domain.usecase

import com.carlos.events.data.repository.EventsRepository
import com.carlos.events.domain.model.EventsDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetEventsUseCaseImpl (
    private val eventsRepository: EventsRepository
): GetEventsUseCase {

    override fun getEvents(): Flow<List<EventsDomain>> = flow {
        val response = eventsRepository.getEvents()
        emit(response)
    }
}