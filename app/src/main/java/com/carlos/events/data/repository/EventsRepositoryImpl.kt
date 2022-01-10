package com.carlos.events.data.repository

import com.carlos.events.data.api.EventsApi
import com.carlos.events.data.response.CheckInResponse
import com.carlos.events.data.response.toDomain
import com.carlos.events.domain.model.EventsDomain

class EventsRepositoryImpl(val service: EventsApi) : EventsRepository {

    override suspend fun getEvents(): List<EventsDomain> {
        return service.getEvents().map { eventResponse ->
            eventResponse.toDomain()
        }
    }

    override suspend fun postCheckIn(checkInResponse: CheckInResponse) {
        service.postCheckIn(checkInResponse)
    }
}