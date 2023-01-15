package com.example.eventdriven

import com.example.eventdriven.events.Event

interface EventListener<out E : Event> {

    suspend fun listen(event: @UnsafeVariance E)
}