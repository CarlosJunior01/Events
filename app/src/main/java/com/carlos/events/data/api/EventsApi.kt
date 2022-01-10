package com.carlos.events.data.api

import com.carlos.events.data.response.CheckInResponse
import com.carlos.events.data.response.EventResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EventsApi {

    @GET("/api/events")
    suspend fun getEvents(): List<EventResponse>

    @POST("/api/checkin")
    suspend fun postCheckIn(@Body checkIn: CheckInResponse)

}