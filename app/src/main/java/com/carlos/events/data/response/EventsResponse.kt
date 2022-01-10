package com.carlos.events.data.response

import com.carlos.events.domain.model.EventsDomain

data class EventResponse(
    val id: String,
    val title: String,
    val description: String,
    val longitude: String,
    val latitude: String,
    val date: Long,
    val price: Float,
    val image: String

)

fun EventResponse.toDomain() = EventsDomain(
    description = description,
    image = image,
    price = price,
    title = title,
    id = id
)

data class BaseResponse<out T>(
    val id: String,
    val title: String,
    val description: String,
    val longitude: String,
    val latitude: String,
    val date: Long,
    val price: Float,
    val image: String
)
