package com.carlos.events.data.repository

import com.carlos.events.data.response.CheckInResponse
import com.carlos.events.domain.model.EventsDomain

interface EventsRepository {
    suspend fun getEvents(): List<EventsDomain>
    suspend fun postCheckIn(checkInResponse: CheckInResponse)
}