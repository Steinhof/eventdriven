package com.example.eventdriven.events

import com.fasterxml.jackson.databind.node.ObjectNode
import java.time.Instant
import java.util.UUID

class CreateApplicationResult(
    override val id: UUID,
    override val initiator: String,
    val timestamp: Instant,
    override val data: ObjectNode,
) : Event