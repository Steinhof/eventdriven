package com.example.eventdriven

import com.example.eventdriven.events.BpmEvent
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service

@Service
class BpmEventsService(private val broker: MQBroker) : EventListener<BpmEvent> {

    @PostConstruct
    fun subscribe() {
        broker.subscribe(BpmEvent::class, this)
    }

    override suspend fun listen(event: BpmEvent) {
        println("BPM_EVENT: $event")
    }
}