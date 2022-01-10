package com.carlos.events.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carlos.events.R
import com.carlos.events.domain.model.EventsViewObject

class EventsAdapter(
    private val events: List<EventsViewObject>,
    private val onItemClickListener: ((events: EventsViewObject) -> Unit)
) : RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    private var imageUrl = String()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.events_item, parent, false)

        return EventViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val events = events[position]

        holder.apply {
            eventTitle.text = events.title
            "R$ ${events.price}".also { eventPrice.text = it }

            itemView.setOnClickListener{
                onItemClickListener.invoke(events)
            }

            Glide.with(itemView)
                .load(convertHttp(events.image))
                .placeholder(R.drawable.placeholder_image)
                .centerCrop()
                .into(eventImage)
        }
    }

    override fun getItemCount(): Int {
        return events.size
    }

    private fun convertHttp(eventsImage: String) = imageUrl.run {
        if (eventsImage.contains(HTTPS)) eventsImage else eventsImage.replace(HTTP, HTTPS) }

    inner class EventViewHolder( itemView: View,
        private val onItemClickListener: ((events: EventsViewObject) -> Unit)
    ) : RecyclerView.ViewHolder(itemView) {
        val eventTitle: TextView = itemView.findViewById(R.id.event_title)
        val eventPrice: TextView = itemView.findViewById(R.id.event_price)
        val eventImage: ImageView = itemView.findViewById(R.id.event_image)
    }

    companion object {
        private const val HTTP = "http"
        private const val HTTPS = "https"
    }
}