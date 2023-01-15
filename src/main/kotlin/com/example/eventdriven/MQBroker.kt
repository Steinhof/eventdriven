package com.example.eventdriven

import com.example.eventdriven.events.Event
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component


@Component
class MQBroker {
    private val log = LoggerFactory.getLogger(javaClass)

    private val processors = ConcurrentHashMap<KClass<out Event>, Set<EventListener<Event>>>()

    fun subscribe(event: KClass<out Event>, processor: EventListener<Event>) {
        val previousProcessors = processors.getOrDefault(event, setOf())
        processors[event] = previousProcessors + processor
    }

    suspend fun publish(event: Event) {
        log.debug("event received: $event")

        val listeners = processors.filter { it.key.isInstance(event) }.values.flatten()
        listeners.forEach { listener ->
            listener.listen(event)
        }
    }

    fun listenResult(): Result<Event> {
        return Result.success()
    }
}