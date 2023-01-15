package com.example.eventdriven

import com.example.eventdriven.events.bpm.ExecuteUsecaseEvent
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class BpmTasksConsumer(private val broker: MQBroker, private val objectMapper: ObjectMapper) {

    @KafkaListener(topics = ["bpm-tasks"])
    suspend fun listen(message: String) {
        val event: ExecuteUsecaseEvent =  objectMapper.readValue(message)
        broker.publish(event)
    }
}