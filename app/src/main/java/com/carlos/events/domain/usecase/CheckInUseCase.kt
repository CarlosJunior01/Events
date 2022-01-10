package com.carlos.events.domain.usecase

import com.carlos.events.data.response.CheckInResponse
import kotlinx.coroutines.flow.Flow

interface CheckInUseCase {
    fun checkIn(checkInResponse: CheckInResponse): Flow<Any>
}