package com.example.eventdriven.events.bpm

import com.example.eventdriven.events.BpmEvent
import com.fasterxml.jackson.databind.node.ObjectNode
import java.util.UUID

class ExecuteUsecaseEvent(
    override val id: UUID,
    override val initiator: String,
    override val data: ObjectNode?
) : BpmEvent