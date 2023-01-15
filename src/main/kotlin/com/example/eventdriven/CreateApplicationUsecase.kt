package com.example.eventdriven

import com.example.eventdriven.events.CreateApplicationExecute
import com.example.eventdriven.events.CreateApplicationResult
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.annotation.PostConstruct
import java.time.Instant
import java.util.UUID
import org.springframework.stereotype.Service

@Service
class CreateApplicationUsecase(
    private val broker: MQBroker,
    private val objectMapper: ObjectMapper
) : EventListener<CreateApplicationExecute> {

    @PostConstruct
    fun subscribe() {
        broker.subscribe(CreateApplicationExecute::class, this)
    }

    override suspend fun listen(event: CreateApplicationExecute) {
        println("CreateApplicationUsecase.execute()")


        val data = objectMapper.createObjectNode()
        data.put("applicationId", UUID.randomUUID().toString())
        val createApplicationResult = CreateApplicationResult(
            id = UUID.randomUUID(), initiator = "user", timestamp = Instant.now(), data = data
        )

        broker.publish(createApplicationResult)
    }
}