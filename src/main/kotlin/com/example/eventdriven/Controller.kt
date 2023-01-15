package com.example.eventdriven

import com.example.eventdriven.events.bpm.StartProcessEvent
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.UUID
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(private val broker: MQBroker, private val objectMapper: ObjectMapper) {

    @PostMapping("/create-application")
    suspend fun createApplication() {
        val data = objectMapper.createObjectNode().put("processDefinition", "Tuva")
        broker.publish(StartProcessEvent(UUID.randomUUID(), "user", data))
    }
}