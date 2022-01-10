package com.carlos.events.domain.model

data class EventsViewObject(
    val description: String,
    val image: String,
    val price: Float,
    val title: String,
    val id: String
) {
    constructor(eventsDomain: EventsDomain) : this(
        description = eventsDomain.description,
        image = eventsDomain.image,
        price = eventsDomain.price,
        title = eventsDomain.title,
        id = eventsDomain.id
    )
}