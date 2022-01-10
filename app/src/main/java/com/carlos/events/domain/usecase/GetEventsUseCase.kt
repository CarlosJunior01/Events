package com.carlos.events.domain.usecase

import com.carlos.events.domain.model.EventsDomain
import kotlinx.coroutines.flow.Flow

interface GetEventsUseCase {
    fun getEvents(): Flow<List<EventsDomain>>
}